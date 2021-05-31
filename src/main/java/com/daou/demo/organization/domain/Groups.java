package com.daou.demo.organization.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "groups")
public class Groups {
    @Id @Column(name = "group_id")
    @GeneratedValue
    private Integer id;
    private String name;
    private String deptCode;

    @Enumerated(EnumType.STRING)
    private GroupType type;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "parent_id")
    private Groups parent;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "parent")
    private List<Groups> children;

    @Builder
    public Groups(String name, String deptCode, GroupType type, Groups parent, List<Groups> children) {
        this.name = name;
        this.deptCode = deptCode;
        this.type = type;
        this.parent = parent;
        this.children = children;
    }
}
