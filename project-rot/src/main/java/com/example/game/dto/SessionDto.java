package com.example.game.dto;

public class SessionDto {
    private Long id;
    private Long userId;
    private Double userLat;
    private Double userLng;
    private Double correctLat;
    private Double correctLng;
    private Integer earnedScore;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public Double getUserLat() { return userLat; }
    public void setUserLat(Double userLat) { this.userLat = userLat; }

    public Double getUserLng() { return userLng; }
    public void setUserLng(Double userLng) { this.userLng = userLng; }

    public Double getCorrectLat() { return correctLat; }
    public void setCorrectLat(Double correctLat) { this.correctLat = correctLat; }

    public Double getCorrectLng() { return correctLng; }
    public void setCorrectLng(Double correctLng) { this.correctLng = correctLng; }

    public Integer getEarnedScore() { return earnedScore; }
    public void setEarnedScore(Integer earnedScore) { this.earnedScore = earnedScore; }
}
