package com.example.game.mongo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "game_session")
public class GameSessionDocument {

    @Id
    private Long id;

    private Long userId;
    private Double userLat;
    private Double userLng;
    private Double correctLat;
    private Double correctLng;
    private Integer earnedScore;

    public GameSessionDocument() {
    }

    public GameSessionDocument(Long id,
                               Long userId,
                               Double userLat,
                               Double userLng,
                               Double correctLat,
                               Double correctLng,
                               Integer earnedScore) {
        this.id = id;
        this.userId = userId;
        this.userLat = userLat;
        this.userLng = userLng;
        this.correctLat = correctLat;
        this.correctLng = correctLng;
        this.earnedScore = earnedScore;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Double getUserLat() {
        return userLat;
    }

    public void setUserLat(Double userLat) {
        this.userLat = userLat;
    }

    public Double getUserLng() {
        return userLng;
    }

    public void setUserLng(Double userLng) {
        this.userLng = userLng;
    }

    public Double getCorrectLat() {
        return correctLat;
    }

    public void setCorrectLat(Double correctLat) {
        this.correctLat = correctLat;
    }

    public Double getCorrectLng() {
        return correctLng;
    }

    public void setCorrectLng(Double correctLng) {
        this.correctLng = correctLng;
    }

    public Integer getEarnedScore() {
        return earnedScore;
    }

    public void setEarnedScore(Integer earnedScore) {
        this.earnedScore = earnedScore;
    }
}
