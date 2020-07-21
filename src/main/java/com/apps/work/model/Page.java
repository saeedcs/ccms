package com.apps.work.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "page")
public class Page {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(length = 255)
    private String pageTitle;

    @Column(columnDefinition="TEXT")
    private String pageBody;

    @Column(length = 100)
    private String author;

    @Column
    private boolean showOnMainPage;

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

    public Page() {
    }

    public Page(int id, String pageTitle, String pageBody, String author, boolean showOnMainPage, Date createdOn, String createdBy, Date changedOn, String changedBy) {
        this.id = id;
        this.pageTitle = pageTitle;
        this.pageBody = pageBody;
        this.author = author;
        this.showOnMainPage = showOnMainPage;
        this.createdOn = createdOn;
        this.createdBy = createdBy;
        this.changedOn = changedOn;
        this.changedBy = changedBy;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPageTitle() {
        return pageTitle;
    }

    public void setPageTitle(String pageTitle) {
        this.pageTitle = pageTitle;
    }

    public String getPageBody() {
        return pageBody;
    }

    public void setPageBody(String pageBody) {
        this.pageBody = pageBody;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public boolean isShowOnMainPage() {
        return showOnMainPage;
    }

    public void setShowOnMainPage(boolean showOnMainPage) {
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

        Page page = (Page) o;

        if (id != page.id) return false;
        if (showOnMainPage != page.showOnMainPage) return false;
        if (pageTitle != null ? !pageTitle.equals(page.pageTitle) : page.pageTitle != null) return false;
        if (pageBody != null ? !pageBody.equals(page.pageBody) : page.pageBody != null) return false;
        if (author != null ? !author.equals(page.author) : page.author != null) return false;
        if (createdOn != null ? !createdOn.equals(page.createdOn) : page.createdOn != null) return false;
        if (createdBy != null ? !createdBy.equals(page.createdBy) : page.createdBy != null) return false;
        if (changedOn != null ? !changedOn.equals(page.changedOn) : page.changedOn != null) return false;
        return changedBy != null ? changedBy.equals(page.changedBy) : page.changedBy == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (pageTitle != null ? pageTitle.hashCode() : 0);
        result = 31 * result + (pageBody != null ? pageBody.hashCode() : 0);
        result = 31 * result + (author != null ? author.hashCode() : 0);
        result = 31 * result + (showOnMainPage ? 1 : 0);
        result = 31 * result + (createdOn != null ? createdOn.hashCode() : 0);
        result = 31 * result + (createdBy != null ? createdBy.hashCode() : 0);
        result = 31 * result + (changedOn != null ? changedOn.hashCode() : 0);
        result = 31 * result + (changedBy != null ? changedBy.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Page{" +
                "id=" + id +
                ", pageTitle='" + pageTitle + '\'' +
                ", pageBody='" + pageBody + '\'' +
                ", author='" + author + '\'' +
                ", showOnMainPage=" + showOnMainPage +
                ", createdOn=" + createdOn +
                ", createdBy='" + createdBy + '\'' +
                ", changedOn=" + changedOn +
                ", changedBy='" + changedBy + '\'' +
                '}';
    }
}
