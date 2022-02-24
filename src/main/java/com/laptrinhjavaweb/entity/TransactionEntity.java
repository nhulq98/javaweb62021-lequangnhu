package com.laptrinhjavaweb.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "transaction")
public class TransactionEntity extends BaseEntity {

    @Column(name = "code")
    private String code;

    @Column(name = "note")
    private String note;

    // ==============Relationship==============
    @ManyToOne(fetch = FetchType.LAZY)// declare relationship type(@OneToMany, @ManyToMany)
    @JoinColumn(name = "customerid")// declare the foreign key name of this table to link with below Object
    private CustomerEntity customer = new CustomerEntity(); // declare relationship object
}
