package com.example.game.mongo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "game_session")
public class GameSessionDocument {
    @Id
    private String id;
    private String userId;
    private Double userLat;
    private Double userLng;
    private Double correctLat;
    private Double correctLng;
    private Integer earnedScore;
    private String errorId;

    public GameSessionDocument() {}

    public GameSessionDocument(
        String id,
        String userId,
        Double userLat,
        Double userLng,
        Double correctLat,
        Double correctLng,
        Integer earnedScore,
        String errorId
    ) {
        this.id = id;
        this.userId = userId;
        this.userLat = userLat;
        this.userLng = userLng;
        this.correctLat = correctLat;
        this.correctLng = correctLng;
        this.earnedScore = earnedScore;
        this.errorId = errorId;
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

    public String getErrorId() {
        return errorId;
    }

    public void setErrorId(String errorId) {
        this.errorId = errorId;
    }
}
