package com.apps.work.model;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "article")
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(length = 255)
    private String articleTitle;

    @Column(columnDefinition="TEXT")
    private String articleBody;

    @Column(length = 100)
    private String author;

    @Column(columnDefinition="TEXT")
    private String excerpt;

    @Column(length = 255)
    private String mainPageImg;

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

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "article", orphanRemoval = true)
    @Cascade({org.hibernate.annotations.CascadeType.ALL })
    private Set<com.apps.work.model.Comments> comments;

    public Article() {
    }

    public Article(Integer id, String articleTitle, String articleBody, String author, String excerpt, String mainPageImg, Date createdOn, String createdBy, Date changedOn, String changedBy, Set<Comments> comments) {
        this.id = id;
        this.articleTitle = articleTitle;
        this.articleBody = articleBody;
        this.author = author;
        this.excerpt = excerpt;
        this.mainPageImg = mainPageImg;
        this.createdOn = createdOn;
        this.createdBy = createdBy;
        this.changedOn = changedOn;
        this.changedBy = changedBy;
        this.comments = comments;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

    public String getArticleBody() {
        return articleBody;
    }

    public void setArticleBody(String articleBody) {
        this.articleBody = articleBody;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getExcerpt() {
        return excerpt;
    }

    public void setExcerpt(String excerpt) {
        this.excerpt = excerpt;
    }

    public String getMainPageImg() {
        return mainPageImg;
    }

    public void setMainPageImg(String mainPageImg) {
        this.mainPageImg = mainPageImg;
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

    public Set<Comments> getComments() {
        return comments;
    }

    public void setComments(Set<Comments> comments) {
        this.comments = comments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Article article = (Article) o;

        if (id != null ? !id.equals(article.id) : article.id != null) return false;
        if (articleTitle != null ? !articleTitle.equals(article.articleTitle) : article.articleTitle != null)
            return false;
        if (articleBody != null ? !articleBody.equals(article.articleBody) : article.articleBody != null) return false;
        if (author != null ? !author.equals(article.author) : article.author != null) return false;
        if (excerpt != null ? !excerpt.equals(article.excerpt) : article.excerpt != null) return false;
        if (mainPageImg != null ? !mainPageImg.equals(article.mainPageImg) : article.mainPageImg != null) return false;
        if (createdOn != null ? !createdOn.equals(article.createdOn) : article.createdOn != null) return false;
        if (createdBy != null ? !createdBy.equals(article.createdBy) : article.createdBy != null) return false;
        if (changedOn != null ? !changedOn.equals(article.changedOn) : article.changedOn != null) return false;
        if (changedBy != null ? !changedBy.equals(article.changedBy) : article.changedBy != null) return false;
        return comments != null ? comments.equals(article.comments) : article.comments == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (articleTitle != null ? articleTitle.hashCode() : 0);
        result = 31 * result + (articleBody != null ? articleBody.hashCode() : 0);
        result = 31 * result + (author != null ? author.hashCode() : 0);
        result = 31 * result + (excerpt != null ? excerpt.hashCode() : 0);
        result = 31 * result + (mainPageImg != null ? mainPageImg.hashCode() : 0);
        result = 31 * result + (createdOn != null ? createdOn.hashCode() : 0);
        result = 31 * result + (createdBy != null ? createdBy.hashCode() : 0);
        result = 31 * result + (changedOn != null ? changedOn.hashCode() : 0);
        result = 31 * result + (changedBy != null ? changedBy.hashCode() : 0);
        result = 31 * result + (comments != null ? comments.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", articleTitle='" + articleTitle + '\'' +
                ", articleBody='" + articleBody + '\'' +
                ", author='" + author + '\'' +
                ", excerpt='" + excerpt + '\'' +
                ", mainPageImg='" + mainPageImg + '\'' +
                ", createdOn=" + createdOn +
                ", createdBy='" + createdBy + '\'' +
                ", changedOn=" + changedOn +
                ", changedBy='" + changedBy + '\'' +
                ", comments=" + comments +
                '}';
    }
}
