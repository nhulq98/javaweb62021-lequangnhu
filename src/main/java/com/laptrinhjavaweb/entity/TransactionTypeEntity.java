package com.laptrinhjavaweb.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "transactiontype")
public class TransactionTypeEntity extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "code")
    private String code;


    // ==============Relationship==============
    @ManyToOne(fetch = FetchType.LAZY)// declare relationship type(@OneToMany, @ManyToMany)
    @JoinColumn(name = "customerid")// declare the foreign key name of this table to link with below Object
    private CustomerEntity customer = new CustomerEntity(); // declare relationship object

    @OneToMany(mappedBy = "transactionType", fetch = FetchType.LAZY)
    private List<TransactionEntity> transaction = new ArrayList<>();
}
