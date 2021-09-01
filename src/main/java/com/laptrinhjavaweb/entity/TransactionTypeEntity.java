package com.laptrinhjavaweb.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "transactiontype")
public class TransactionTypeEntity extends BaseEntity{

    @Column(name = "name")
    private String name;

    @Column(name = "code", unique = true)
    private String code;

    // Quan hệ 1-n với đối tượng ở dưới (building) (1 địa điểm có nhiều building)
    @OneToMany(mappedBy = "transactionType", fetch = FetchType.LAZY) // khai báo kiểu quan hệ và tên đối tượng sẽ liên kết với bảng này
    private List<TransactionEntity> transactions = new ArrayList<>();// khai báo đối tượng quan hệ

    public List<TransactionEntity> getTransactions() {
        return transactions;
    }
    public void setTransactions(List<TransactionEntity> transactions) {
        this.transactions = transactions;
    }

    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

}
