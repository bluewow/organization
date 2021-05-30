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

        recursive(groups);

        assertThat(list).containsExactly(
                "ABC 회사", "경영지원본부", "인사팀", "총무팀", "법무팀",
                "SW 개발본부", "플랫폼개발부", "비즈플랫폼팀", "비즈서비스팀", "그룹웨어개발팀",
                "비즈서비스개발부", "플랫폼서비스팀", "모바일개발팀");
    }

    @DisplayName("부서코드 파라미터를 추가했을때 해당부서를 포함한 하위부서를 응답한다-2")
    @Test
    public void test1() {
        Groups groups = groupsRepository.findByDeptCode("A");

        recursive(groups);

        assertThat(list).containsExactly("경영지원본부", "인사팀", "총무팀", "법무팀");
    }

    private void recursive(Groups groups) {
        if(groups == null) return;
        list.add(groups.getName());
//      System.out.println(g.getName());

        for(Groups children : groups.getChildren()) {
            if(children.getParent() != null) {
                recursive(children);
            }
        }
    }
}
