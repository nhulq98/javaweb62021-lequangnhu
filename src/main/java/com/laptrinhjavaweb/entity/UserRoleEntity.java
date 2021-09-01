package com.laptrinhjavaweb.entity;

import javax.persistence.*;

/*@Entity
@Table(name = "user_role")*/
public class UserRoleEntity extends BaseEntity {

/*
    @Column(name = "roleid", nullable = false)
    private Long roleId;

    @Column(name = "userid", nullable = false)
    private Long userId;

    //relationship
    @ManyToOne// khai báo kiểu quan hệ
    @JoinColumn(name = "roleid", nullable = false, referencedColumnName="id", insertable=false, updatable=false)// Khai báo TÊN khóa ngoại của bảng này sẽ liên kết với đối tượng bên dưới
    private RoleEntity role; // khai báo đối tượng quan hệ

    @ManyToOne// khai báo kiểu quan hệ
    @JoinColumn(name = "userid", nullable = false, referencedColumnName="id", insertable=false, updatable=false)// Khai báo TÊN khóa ngoại của bảng này sẽ liên kết với đối tượng bên dưới
    private UserEntity user; // khai báo đối tượng quan hệ

    public RoleEntity getRole() {
        return role;
    }

    public void setRole(RoleEntity role) {
        this.role = role;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public Long getRoleId() {
        return roleId;
    }
    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
*/

}
