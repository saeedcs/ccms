package com.apps.work.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "comments")
public class Comments {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(columnDefinition="TEXT")
    private String commentText;

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

    @ManyToOne(fetch=FetchType.LAZY, cascade={ CascadeType.MERGE })
    @JoinColumn(name = "article_id")
    private Article article;

    public Comments() {
    }

    public Comments(Integer id, String commentText, Date createdOn, String createdBy, Date changedOn, String changedBy, Article article) {
        this.id = id;
        this.commentText = commentText;
        this.createdOn = createdOn;
        this.createdBy = createdBy;
        this.changedOn = changedOn;
        this.changedBy = changedBy;
        this.article = article;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
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

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Comments comments = (Comments) o;

        if (id != null ? !id.equals(comments.id) : comments.id != null) return false;
        if (commentText != null ? !commentText.equals(comments.commentText) : comments.commentText != null)
            return false;
        if (createdOn != null ? !createdOn.equals(comments.createdOn) : comments.createdOn != null) return false;
        if (createdBy != null ? !createdBy.equals(comments.createdBy) : comments.createdBy != null) return false;
        if (changedOn != null ? !changedOn.equals(comments.changedOn) : comments.changedOn != null) return false;
        if (changedBy != null ? !changedBy.equals(comments.changedBy) : comments.changedBy != null) return false;
        return article != null ? article.equals(comments.article) : comments.article == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (commentText != null ? commentText.hashCode() : 0);
        result = 31 * result + (createdOn != null ? createdOn.hashCode() : 0);
        result = 31 * result + (createdBy != null ? createdBy.hashCode() : 0);
        result = 31 * result + (changedOn != null ? changedOn.hashCode() : 0);
        result = 31 * result + (changedBy != null ? changedBy.hashCode() : 0);
        result = 31 * result + (article != null ? article.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "com.apps.work.model.Comments{" +
                "id=" + id +
                ", commentText='" + commentText + '\'' +
                ", createdOn=" + createdOn +
                ", createdBy='" + createdBy + '\'' +
                ", changedOn=" + changedOn +
                ", changedBy='" + changedBy + '\'' +
                ", article=" + article +
                '}';
    }
}
