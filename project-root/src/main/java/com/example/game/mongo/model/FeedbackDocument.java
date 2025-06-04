package com.example.game.mongo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "feedback")
public class FeedbackDocument {
    @Id
    private String id;
    private String userId;
    private String description;
    private Integer rating;
    private String problem;

    public FeedbackDocument() {}

    public FeedbackDocument(String id, String userId, String description, Integer rating, String problem) {
        this.id = id;
        this.userId = userId;
        this.description = description;
        this.rating = rating;
        this.problem = problem;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getProblem() {
        return problem;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }
}
