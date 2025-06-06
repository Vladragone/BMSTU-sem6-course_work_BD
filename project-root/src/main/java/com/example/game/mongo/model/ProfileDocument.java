package com.example.game.mongo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "profile")
public class ProfileDocument {

    @Id
    private Long id;

    private Long userId;
    private int score;
    private LocalDateTime regDate;
    private int gameNum;

    public ProfileDocument() {
    }

    public ProfileDocument(Long id, Long userId, LocalDateTime regDate) {
        this.id = id;
        this.userId = userId;
        this.regDate = regDate;
        this.score = 0;
        this.gameNum = 0;
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

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
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
