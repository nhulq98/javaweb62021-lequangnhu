package com.laptrinhjavaweb.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "customer")
public class CustomerEntity extends BaseEntity {

    @Column(name = "fullname")
    private String fullName;

    @Column(name = "phone", unique = true)
    private String phone;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "company")
    private String company;

    @Column(name = "demand")
    private String demand;

    @Column(name = "note")
    private String note;

    @Column(name = "status")
    private String status;

    //=========================relationship================================
//    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
//    List<AssignmentCustomerEntity> assignmentCustomers = new ArrayList<>();
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "staff_customer",
            joinColumns = @JoinColumn(name = "customerid", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "staffid", nullable = false))
    private List<UserEntity> staffs = new ArrayList<>();

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
    List<TransactionTypeEntity> transactionTypes = new ArrayList<>();
}
