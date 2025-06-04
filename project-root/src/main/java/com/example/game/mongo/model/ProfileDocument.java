package com.example.game.mongo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "profile")
public class ProfileDocument {
    @Id
    private String id;
    private String userId;
    private LocalDateTime regDate;
    private int gameNum;

    public ProfileDocument() {}

    public ProfileDocument(String id, String userId, LocalDateTime regDate, int gameNum) {
        this.id = id;
        this.userId = userId;
        this.regDate = regDate;
        this.gameNum = gameNum;
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

    public LocalDateTime getRegDate() {
        return regDate;
    }

    public void setRegDate(LocalDateTime regDate) {
        this.regDate = regDate;
    }

    public int getGameNum() {
        return gameNum;
    }

    public void setGameNum(int gameNum) {
        this.gameNum = gameNum;
    }
}
