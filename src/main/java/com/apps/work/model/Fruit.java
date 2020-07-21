package com.apps.work.model;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name = "fruit")
public class Fruit {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(length = 15)
    private String taste;

    @Column(name = "purchase_date_time", nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    private Date purchaseDateTime;

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

    public Fruit() {
        super();
    }

    public Fruit(Integer id, String taste, Date purchaseDateTime, Date createdOn, String createdBy, Date changedOn, String changedBy) {
        this.id = id;
        this.taste = taste;
        this.purchaseDateTime = purchaseDateTime;
        this.createdOn = createdOn;
        this.createdBy = createdBy;
        this.changedOn = changedOn;
        this.changedBy = changedBy;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTaste() {
        return taste;
    }

    public void setTaste(String taste) {
        this.taste = taste;
    }

    public Date getPurchaseDateTime() {
        return purchaseDateTime;
    }

    public void setPurchaseDateTime(Date purchaseDateTime) {
        this.purchaseDateTime = purchaseDateTime;
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

        Fruit fruit = (Fruit) o;

        if (id != null ? !id.equals(fruit.id) : fruit.id != null) return false;
        if (taste != null ? !taste.equals(fruit.taste) : fruit.taste != null) return false;
        if (purchaseDateTime != null ? !purchaseDateTime.equals(fruit.purchaseDateTime) : fruit.purchaseDateTime != null)
            return false;
        if (createdOn != null ? !createdOn.equals(fruit.createdOn) : fruit.createdOn != null) return false;
        if (createdBy != null ? !createdBy.equals(fruit.createdBy) : fruit.createdBy != null) return false;
        if (changedOn != null ? !changedOn.equals(fruit.changedOn) : fruit.changedOn != null) return false;
        return changedBy != null ? changedBy.equals(fruit.changedBy) : fruit.changedBy == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (taste != null ? taste.hashCode() : 0);
        result = 31 * result + (purchaseDateTime != null ? purchaseDateTime.hashCode() : 0);
        result = 31 * result + (createdOn != null ? createdOn.hashCode() : 0);
        result = 31 * result + (createdBy != null ? createdBy.hashCode() : 0);
        result = 31 * result + (changedOn != null ? changedOn.hashCode() : 0);
        result = 31 * result + (changedBy != null ? changedBy.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Fruit{" +
                "id=" + id +
                ", taste='" + taste + '\'' +
                ", purchaseDateTime=" + purchaseDateTime +
                ", createdOn=" + createdOn +
                ", createdBy='" + createdBy + '\'' +
                ", changedOn=" + changedOn +
                ", changedBy='" + changedBy + '\'' +
                '}';
    }
}