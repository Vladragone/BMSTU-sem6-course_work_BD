package com.example.game.persistence.mongo.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "feedback")
public class FeedbackDocument {
    @Id
    private Long id;
    private Long userId;
    private Integer rating;
    private String problem;
    private String description;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public Integer getRating() { return rating; }
    public void setRating(Integer rating) { this.rating = rating; }

    public String getProblem() { return problem; }
    public void setProblem(String problem) { this.problem = problem; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}
