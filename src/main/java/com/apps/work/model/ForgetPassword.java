package com.apps.work.model;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "forget_password")
public class ForgetPassword {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 100)
    private String username;

    @Column(length = 155)
    private String code;

    @Column
    private boolean used;

    @Column(name = "created_on", nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdOn;

    @Column(length = 100)
    private String createdBy;

    @Column(name = "changed_on", nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    private Date changedOn;

    @Column(length = 100)
    private String changedBy;

    public ForgetPassword() {
    }

    public ForgetPassword(Long id, String username, String code, boolean used, Date createdOn, String createdBy, Date changedOn, String changedBy) {
        this.id = id;
        this.username = username;
        this.code = code;
        this.used = used;
        this.createdOn = createdOn;
        this.createdBy = createdBy;
        this.changedOn = changedOn;
        this.changedBy = changedBy;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getChangedOn() {
        return changedOn;
    }

    public void setChangedOn(Date changedOn) {
        this.changedOn = changedOn;
    }

    public String getChangedBy() {
        return changedBy;
    }

    public void setChangedBy(String changedBy) {
        this.changedBy = changedBy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ForgetPassword that = (ForgetPassword) o;

        if (used != that.used) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (username != null ? !username.equals(that.username) : that.username != null) return false;
        if (code != null ? !code.equals(that.code) : that.code != null) return false;
        if (createdOn != null ? !createdOn.equals(that.createdOn) : that.createdOn != null) return false;
        if (createdBy != null ? !createdBy.equals(that.createdBy) : that.createdBy != null) return false;
        if (changedOn != null ? !changedOn.equals(that.changedOn) : that.changedOn != null) return false;
        return changedBy != null ? changedBy.equals(that.changedBy) : that.changedBy == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (code != null ? code.hashCode() : 0);
        result = 31 * result + (used ? 1 : 0);
        result = 31 * result + (createdOn != null ? createdOn.hashCode() : 0);
        result = 31 * result + (createdBy != null ? createdBy.hashCode() : 0);
        result = 31 * result + (changedOn != null ? changedOn.hashCode() : 0);
        result = 31 * result + (changedBy != null ? changedBy.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ForgetPassword{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", code='" + code + '\'' +
                ", used=" + used +
                ", createdOn=" + createdOn +
                ", createdBy='" + createdBy + '\'' +
                ", changedOn=" + changedOn +
                ", changedBy='" + changedBy + '\'' +
                '}';
    }
}
