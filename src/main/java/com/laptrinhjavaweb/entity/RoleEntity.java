package com.laptrinhjavaweb.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "role")
public class RoleEntity extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String code;

    // ==============Relationship==============
    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
// declare the mappedBy have to the same with the variable of RoleEntity side
    private List<UserEntity> users = new ArrayList<>();
}
