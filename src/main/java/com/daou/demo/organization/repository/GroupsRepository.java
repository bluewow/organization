package com.daou.demo.organization.repository;

import com.daou.demo.organization.domain.Groups;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GroupsRepository extends JpaRepository<Groups, Long> {
    Groups findByDeptCode(String deptCode);
    Groups findRootByParentIdIsNull();

    List<Groups> findByNameContains(String searchKeyword);
}