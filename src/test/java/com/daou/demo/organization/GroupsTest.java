package com.daou.demo.organization;

import com.daou.demo.organization.domain.GroupType;
import com.daou.demo.organization.domain.Groups;
import com.daou.demo.organization.repository.GroupsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@Sql("classpath:/schema.sql")
@Sql("classpath:/data.sql")
@Transactional
@SpringBootTest
public class GroupsTest {

    private ArrayList<String> list = new ArrayList<>();
    private Set<String> set = new HashSet<>();

    @Autowired
    private GroupsRepository groupsRepository;

    @BeforeEach
    public void setUp() {
        list.clear();
        set.clear();
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
        Groups groups = groupsRepository.findByDeptCode("A").get();

        recursive(groups, true);

        assertThat(list).containsExactly("경영지원본부", "인사팀", "총무팀", "법무팀");
    }

    @DisplayName("부서정보만 반환")
    @Test
    public void test2() {
        Groups groups = groupsRepository.findByDeptCode("B2B2").get();

        recursive(groups, true);

        assertThat(list).containsExactly("모바일개발팀");
    }

    @DisplayName("부서정보와 부서원 반환-1")
    @Test
    public void test3() {
        Groups groups = groupsRepository.findByDeptCode("B2B2").get();

        recursive(groups, false);

        assertThat(list).containsExactly("모바일개발팀", "모바일개발 인원1");
    }

    @DisplayName("부서정보와 부서원 반환-2")
    @Test
    public void test4() {
        Groups groups = groupsRepository.findByDeptCode("A").get();

        recursive(groups, false);

        assertThat(list).containsExactly(
                "경영지원본부",
                "인사팀", "총무팀", "총무팀 인원1",
                "법무팀", "법무팀 인원1", "법무팀 인원2");
    }

    @DisplayName("검색 파라미터 추가시(searchType, searchKeyword) " +
            "검색된 부서들과 관계된 상위 부서를 포함한 트리구조로 응답한다 - 부서")
    @Test
    public void test5() {
        String searchKeyword = "플랫폼";
        List<Groups> rawGroups = groupsRepository.findByNameContains(searchKeyword);
        List<Groups> groupsList = rawGroups.stream()
                .filter(g -> g.getType() != GroupType.Member)
                .collect(Collectors.toList());

        groupsList.forEach(g -> reverseRecursive(g));

        assertThat(set).hasSize(6);
        assertThat(set).contains(
                "ABC 회사",
                "SW 개발본부",
                "플랫폼개발부", "비즈플랫폼팀",
                "비즈서비스개발부", "플랫폼서비스팀");
    }

    @DisplayName("검색 파라미터 추가시(searchType, searchKeyword) " +
            "검색된 부서들과 관계된 상위 부서를 포함한 트리구조로 응답한다 - 부서원")
    @Test
    public void test6() {
        String searchKeyword = "플랫폼";
        List<Groups> rawGroups = groupsRepository.findByNameContains(searchKeyword);
        List<Groups> groupsList = rawGroups.stream()
                .filter(g -> g.getType() == GroupType.Member)
                .collect(Collectors.toList());

        groupsList.forEach(g -> reverseRecursive(g));

        assertThat(set).hasSize(4);
        assertThat(set).contains(
                "ABC 회사", "SW 개발본부",
                "비즈서비스개발부", "플랫폼서비스팀");
    }

    private void reverseRecursive(Groups groups) {
        if(groups.getParent() == null) {
            if(groups.getType() != GroupType.Member)
                set.add(groups.getName()); //최상위 노드를 저장하고 재귀를 종료한다

            return;
        }
        if(groups.getType() != GroupType.Member)
            set.add(groups.getName());

        reverseRecursive(groups.getParent());
    }

    private void recursive(Groups groups, Boolean departmentOnly) {
        if(groups == null) return;
        if(isDepartment(departmentOnly, groups))
            list.add(groups.getName());

        for(Groups children : groups.getChildren()) {
            if(children.getParent() != null) {
                recursive(children, departmentOnly);
            }
        }
    }

    private Boolean isDepartment(Boolean departmentOnly, Groups g) {
        if(departmentOnly) {
            if(g.getType() == GroupType.Member)
                return false;
            else
                return true;
        }
        return true;
    }
}
