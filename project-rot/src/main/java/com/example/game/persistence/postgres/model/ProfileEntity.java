package com.example.game.persistence.postgres.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "profile")
public class ProfileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false, unique = true)
    private Long userId;

    @Column(nullable = false)
    private int score = 0;

    @Column(name = "reg_date", nullable = false)
    private LocalDateTime regDate;

    @Column(name = "game_num", nullable = false)
    private int gameNum = 0;

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
