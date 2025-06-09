package com.example.game.dto;

import java.time.LocalDateTime;

public class ProfileDto {
    private Long id;
    private Long userId;
    private int score;
    private LocalDateTime regDate;
    private int gameNum;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public int getScore() { return score; }
    public void setScore(int score) { this.score = score; }

    public LocalDateTime getRegDate() { return regDate; }
    public void setRegDate(LocalDateTime regDate) { this.regDate = regDate; }

    public int getGameNum() { return gameNum; }
    public void setGameNum(int gameNum) { this.gameNum = gameNum; }
}
