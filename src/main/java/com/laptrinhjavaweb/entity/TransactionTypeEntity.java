package com.laptrinhjavaweb.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "transactiontype")
public class TransactionTypeEntity extends BaseEntity {

    @Column(name = "name")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)// khai báo kiểu quan hệ
    @JoinColumn(name = "customerid")// Khai báo TÊN khóa ngoại của bảng này sẽ liên kết với đối tượng bên dưới
    private CustomerEntity customer = new CustomerEntity(); // khai báo đối tượng quan hệ

    @OneToMany(mappedBy = "transactionType", fetch = FetchType.LAZY)
    private List<TransactionEntity> transaction = new ArrayList<>();
}
