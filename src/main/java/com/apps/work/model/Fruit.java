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
    private int id;

    @Column(length = 15)
    private String taste;

    @Basic(optional = true)
    @Column(name = "purchase_date_time", insertable = true, updatable = true)
    @Temporal(TemporalType.TIMESTAMP)
    private Date purchaseDateTime;

    private boolean isDone;

    public Fruit() {
        super();
    }

    public Fruit(int id, String taste, Date purchaseDateTime, boolean isDone) {
        this.id = id;
        this.taste = taste;
        this.purchaseDateTime = purchaseDateTime;
        this.isDone = isDone;
    }

    public Date getPurchaseDateTime() {
        return purchaseDateTime;
    }

    public void setPurchaseDateTime(Date purchaseDateTime) {
        this.purchaseDateTime = purchaseDateTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTaste() {
        return taste;
    }

    public void setTaste(String taste) {
        this.taste = taste;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Fruit other = (Fruit) obj;
        if (id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Fruit{" +
                "id=" + id +
                ", taste='" + taste + '\'' +
                ", purchaseDateTime=" + purchaseDateTime +
                ", isDone=" + isDone +
                '}';
    }
}