package com.laptrinhjavaweb.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "transaction")
public class TransactionEntity extends BaseEntity {

    @Column(name = "note")
    private String note;

    // ==============Relationship==============
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "typeid")
    private TransactionTypeEntity transactionType = new TransactionTypeEntity();
}
