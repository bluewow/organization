package com.daou.demo.organization.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "groups")
public class Groups {
    @Id @Column(name = "group_id")
    @GeneratedValue
    private Long id;
    private String name;
    private String deptCode;

    @Enumerated(EnumType.STRING)
    private GroupType type;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "parent_id")
    private Groups parent;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "parent")
    private List<Groups> children;
}
