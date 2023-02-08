package com.example.angel1.Model;

public class ScholarshipsModel {
    String title;
    String dateCreated;
    String description;
    String deadline;
    String message;

    public ScholarshipsModel() {
    }

    public ScholarshipsModel(String title, String dateCreated, String description, String deadline, String message) {
        this.title = title;
        this.dateCreated = dateCreated;
        this.description = description;
        this.deadline = deadline;
        this.message = message;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
