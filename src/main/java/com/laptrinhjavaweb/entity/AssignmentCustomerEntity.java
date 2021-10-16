package com.laptrinhjavaweb.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "assignmentcustomer")
public class AssignmentCustomerEntity extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)// declare relationship type
    @JoinColumn(name = "staffid")// declare the foreign key name of this table to link with below Object
    private UserEntity user = new UserEntity(); // declare relationship object

    @ManyToOne(fetch = FetchType.LAZY)// declare relationship type
    @JoinColumn(name = "customerid")// declare the foreign key name of this table to link with below Object
    private CustomerEntity customer = new CustomerEntity(); // declare relationship object

}
