package com.apps.work.model;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "todo")
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(length = 15)
    private String user;

    @Size(min = 10, message = "Enter atleast 10 Characters.")
    private String description;

    private Date targetDate;

    private boolean isDone;

    public Todo() {
        super();
    }

    public Todo(Integer id, String user, @Size(min = 10, message = "Enter atleast 10 Characters.") String description, Date targetDate, boolean isDone) {
        this.id = id;
        this.user = user;
        this.description = description;
        this.targetDate = targetDate;
        this.isDone = isDone;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getTargetDate() {
        return targetDate;
    }

    public void setTargetDate(Date targetDate) {
        this.targetDate = targetDate;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Todo todo = (Todo) o;

        if (isDone != todo.isDone) return false;
        if (id != null ? !id.equals(todo.id) : todo.id != null) return false;
        if (user != null ? !user.equals(todo.user) : todo.user != null) return false;
        if (description != null ? !description.equals(todo.description) : todo.description != null) return false;
        return targetDate != null ? targetDate.equals(todo.targetDate) : todo.targetDate == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (targetDate != null ? targetDate.hashCode() : 0);
        result = 31 * result + (isDone ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Todo{" +
                "id=" + id +
                ", user='" + user + '\'' +
                ", description='" + description + '\'' +
                ", targetDate=" + targetDate +
                ", isDone=" + isDone +
                '}';
    }
}