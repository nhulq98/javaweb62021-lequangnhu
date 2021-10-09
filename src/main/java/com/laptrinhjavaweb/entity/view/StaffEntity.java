package com.laptrinhjavaweb.entity.view;

import com.laptrinhjavaweb.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

//@MappedSuperclass
//@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@Entity
@Table
public class StaffEntity extends BaseEntity {
	
    @Column(name = "fullname")
    private String fullName;
    
    @Column(name = "checked")
    private String checked;
}
