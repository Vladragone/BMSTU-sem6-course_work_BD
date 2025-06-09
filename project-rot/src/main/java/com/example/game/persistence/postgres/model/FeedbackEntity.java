package com.example.game.persistence.postgres.model;

import jakarta.persistence.*;

@Entity
@Table(name = "feedback")
public class FeedbackEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    private Integer rating;

    @Column(name = "problem")
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
