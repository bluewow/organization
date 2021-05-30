package com.daou.demo.organization;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

@Sql("classpath:/schema.sql")
@Sql("classpath:/data.sql")
@Transactional
@SpringBootTest
public class GroupsTest {

    private ArrayList<String> list = new ArrayList<>();

    @Autowired
    private GroupsRepository groupsRepository;

    @BeforeEach
    public void setUp() {
        list.clear();
    }

    @DisplayName("부서코드 파라미터를 추가했을때 해당부서를 포함한 하위부서를 응답한다-1")
    @Test
    public void test() {
        Groups groups = groupsRepository.findRootByParentIdIsNull();

        recursive(groups, true);

        assertThat(list).containsExactly(
                "ABC 회사", "경영지원본부", "인사팀", "총무팀", "법무팀",
                "SW 개발본부", "플랫폼개발부", "비즈플랫폼팀", "비즈서비스팀", "그룹웨어개발팀",
                "비즈서비스개발부", "플랫폼서비스팀", "모바일개발팀");
    }

    @DisplayName("부서코드 파라미터를 추가했을때 해당부서를 포함한 하위부서를 응답한다-2")
    @Test
    public void test1() {
        Groups groups = groupsRepository.findByDeptCode("A");

        recursive(groups, true);

        assertThat(list).containsExactly("경영지원본부", "인사팀", "총무팀", "법무팀");
    }

    @DisplayName("부서정보만 반환")
    @Test
    public void test2() {
        Groups groups = groupsRepository.findByDeptCode("B2B2");

        recursive(groups, true);

        assertThat(list).containsExactly("모바일개발팀");
    }

    @DisplayName("부서정보와 부서원 반환-1")
    @Test
    public void test3() {
        Groups groups = groupsRepository.findByDeptCode("B2B2");

        recursive(groups, false);

        assertThat(list).containsExactly("모바일개발팀", "모바일개발 인원1");
    }

    @DisplayName("부서정보와 부서원 반환-2")
    @Test
    public void test4() {
        Groups groups = groupsRepository.findByDeptCode("A");

        recursive(groups, false);

        assertThat(list).containsExactly(
                "경영지원본부",
                "인사팀", "총무팀", "총무팀 인원1",
                "법무팀", "법무팀 인원1", "법무팀 인원2");
    }

    private void recursive(Groups groups, Boolean departmentOnly) {
        if(groups == null) return;
        if(isDepartMent(departmentOnly, groups))
            list.add(groups.getName());

        for(Groups children : groups.getChildren()) {
            if(children.getParent() != null) {
                recursive(children, departmentOnly);
            }
        }
    }

    private Boolean isDepartMent(Boolean departmentOnly, Groups g) {
        if(departmentOnly) {
            if(g.getType() == GroupType.Member)
                return false;
            else
                return true;
        }

        return true;
    }
}
