package com.laptrinhjavaweb.entity.view;

import com.laptrinhjavaweb.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table
public class StaffEntity extends BaseEntity {
    @Column(name = "fullname")
    private String fullName;
}
