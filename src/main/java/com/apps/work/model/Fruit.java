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

    @Column(name = "purchase_date_time", nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    private Date purchaseDateTime;

    public Fruit() {
        super();
    }

    public Fruit(int id, String taste, Date purchaseDateTime) {
        this.id = id;
        this.taste = taste;
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

    public Date getPurchaseDateTime() {
        return purchaseDateTime;
    }

    public void setPurchaseDateTime(Date purchaseDateTime) {
        this.purchaseDateTime = purchaseDateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Fruit fruit = (Fruit) o;

        if (id != fruit.id) return false;
        if (taste != null ? !taste.equals(fruit.taste) : fruit.taste != null) return false;
        return purchaseDateTime != null ? purchaseDateTime.equals(fruit.purchaseDateTime) : fruit.purchaseDateTime == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (taste != null ? taste.hashCode() : 0);
        result = 31 * result + (purchaseDateTime != null ? purchaseDateTime.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Fruit{" +
                "id=" + id +
                ", taste='" + taste + '\'' +
                ", purchaseDateTime=" + purchaseDateTime +
                '}';
    }
}