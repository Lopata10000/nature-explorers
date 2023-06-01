package com.fanta.natureexplorers.entity;

import com.fanta.natureexplorers.enumrole.ManagerStatus;
import com.fanta.natureexplorers.enumrole.UserRole;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

@Entity
@Table(name = "managers")
@TypeDefs({@TypeDef(name = "pgsql_enum", typeClass = org.hibernate.type.EnumType.class)})
public class Manager {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "manager_id")
    private int managerId;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    @Type(type = "pgsql_enum")
    private ManagerStatus status = ManagerStatus.ACTIVE;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", unique = true)
    private User user;

    public Manager(User user) {
        this.user = user;
    }

    public Manager(User user, ManagerStatus status) {
        this.user = user;
        this.status = status;
    }

    public Manager() {}

    public int getManagerId() {
        return managerId;
    }

    public void setManagerId(int managerId) {
        this.managerId = managerId;
    }

    public User getUser() {
        return user;
    }

    public int getUserId() {
        return user.getUserId();
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ManagerStatus getStatus() {
        return status;
    }

    public void setStatus(ManagerStatus status) {
        this.status = status;
    }

    @PrePersist
    private void setDefaultRole() {
        if (status == null || !isValidUserRole(status.name())) {
            status = ManagerStatus.ACTIVE;
        }
    }

    private boolean isValidUserRole(String value) {
        try {
            UserRole.valueOf(value);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
