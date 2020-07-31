package com.apps.work.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(length = 100)
    private String catName;

    @Column
    private Boolean showOnMainPage;

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

    public Category() {
    }

    public Category(Integer id, String catName, Boolean showOnMainPage, Date createdOn, String createdBy, Date changedOn, String changedBy) {
        this.id = id;
        this.catName = catName;
        this.showOnMainPage = showOnMainPage;
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

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public Boolean isShowOnMainPage() {
        return showOnMainPage;
    }

    public void setShowOnMainPage(Boolean showOnMainPage) {
        this.showOnMainPage = showOnMainPage;
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

        Category category = (Category) o;

        if (showOnMainPage != category.showOnMainPage) return false;
        if (id != null ? !id.equals(category.id) : category.id != null) return false;
        if (catName != null ? !catName.equals(category.catName) : category.catName != null) return false;
        if (createdOn != null ? !createdOn.equals(category.createdOn) : category.createdOn != null) return false;
        if (createdBy != null ? !createdBy.equals(category.createdBy) : category.createdBy != null) return false;
        if (changedOn != null ? !changedOn.equals(category.changedOn) : category.changedOn != null) return false;
        return changedBy != null ? changedBy.equals(category.changedBy) : category.changedBy == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (catName != null ? catName.hashCode() : 0);
        result = 31 * result + (showOnMainPage ? 1 : 0);
        result = 31 * result + (createdOn != null ? createdOn.hashCode() : 0);
        result = 31 * result + (createdBy != null ? createdBy.hashCode() : 0);
        result = 31 * result + (changedOn != null ? changedOn.hashCode() : 0);
        result = 31 * result + (changedBy != null ? changedBy.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", catName='" + catName + '\'' +
                ", showOnMainPage=" + showOnMainPage +
                ", createdOn=" + createdOn +
                ", createdBy='" + createdBy + '\'' +
                ", changedOn=" + changedOn +
                ", changedBy='" + changedBy + '\'' +
                '}';
    }
}
