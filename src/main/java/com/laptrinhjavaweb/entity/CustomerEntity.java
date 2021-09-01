package com.laptrinhjavaweb.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "customer")
public class CustomerEntity extends BaseEntity{

    @Column(name = "fullname")
    private String fullName;

    @Column(name = "phone", unique = true)
    private String phone;

    @Column(name = "email", unique = true)
    private String email;

    // relationship
    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
    List<AssignmentCustomerEntity> assignmentCustomers = new ArrayList<>();

    // relationship
    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
    List<TransactionEntity> transactions = new ArrayList<>();

    public List<TransactionEntity> getTransactions() {
        return transactions;
    }
    public void setTransactions(List<TransactionEntity> transactions) {
        this.transactions = transactions;
    }

    public String getFullName() {
        return fullName;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public List<AssignmentCustomerEntity> getAssignmentCustomers() {
        return assignmentCustomers;
    }
    public void setAssignmentCustomers(List<AssignmentCustomerEntity> assignmentCustomers) {
        this.assignmentCustomers = assignmentCustomers;
    }
}
