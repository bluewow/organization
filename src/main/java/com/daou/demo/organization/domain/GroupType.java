package com.daou.demo.organization.domain;

import lombok.Getter;

@Getter
public enum GroupType {
    Company(1),
    Division(2),
    Department(3),
    Member(4);

    Integer i;

    GroupType(Integer i) {
        this.i = i;
    }
}
