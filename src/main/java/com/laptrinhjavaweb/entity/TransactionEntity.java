package com.laptrinhjavaweb.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "transaction")
public class TransactionEntity extends BaseEntity {

    private static final long serialVersionUID = -6525302831793188081L;

    @Column(name = "note")
    private String note;

    @Column(name = "customerid", nullable = false)
    private Long customerId;

    @Column(name = "type", nullable = false)
    private Long type;

    @ManyToOne(fetch = FetchType.LAZY)// khai báo kiểu quan hệ
    @JoinColumn(name = "customerid", nullable = false, referencedColumnName="id", insertable=false, updatable=false)// Khai báo TÊN khóa ngoại của bảng này sẽ liên kết với đối tượng bên dưới
    private CustomerEntity customer = new CustomerEntity(); // khai báo đối tượng quan hệ

    @ManyToOne(fetch = FetchType.LAZY)// khai báo kiểu quan hệ
    @JoinColumn(name = "type", nullable = false, referencedColumnName="id", insertable=false, updatable=false)// Khai báo TÊN khóa ngoại của bảng này sẽ liên kết với đối tượng bên dưới
    private TransactionTypeEntity transactionType = new TransactionTypeEntity(); // khai báo đối tượng quan hệ

    public TransactionTypeEntity getTransactionType() {
        return transactionType;
    }
    public void setTransactionType(TransactionTypeEntity transactionType) {
        this.transactionType = transactionType;
    }

    public CustomerEntity getCustomer() {
        return customer;
    }
    public void setCustomer(CustomerEntity customer) {
        this.customer = customer;
    }

    public String getNote() {
        return note;
    }
    public void setNote(String note) {
        this.note = note;
    }

    public Long getCustomerId() {
        return customerId;
    }
    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getType() {
        return type;
    }
    public void setType(Long type) {
        this.type = type;
    }
}
