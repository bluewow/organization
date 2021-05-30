package com.daou.demo.organization;

import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupsRepository extends JpaRepository<Groups, Long> {
    Groups findByDeptCode(String deptCode);
    Groups findRootByParentIdIsNull();
}
