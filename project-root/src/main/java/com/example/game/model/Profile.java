package com.example.game.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "profile")
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Связь один к одному с пользователем (FK из таблицы users)
    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", unique = true, nullable = false)
    private User user;

    // Начальный счёт 0
    @Column(nullable = false)
    private int score = 0;

    // Дата регистрации (заполняется при регистрации пользователя)
    @Column(name = "reg_date", nullable = false)
    private LocalDateTime regDate;

    // Количество сыгранных игр, по умолчанию 0
    @Column(name = "game_num", nullable = false)
    private int gameNum = 0;

    public Profile() {
    }

    // Конструктор для создания профиля с данными пользователя и датой регистрации
    public Profile(User user, LocalDateTime regDate) {
        this.user = user;
        this.regDate = regDate;
        this.score = 0;
        this.gameNum = 0;
    }

    // Геттеры и сеттеры

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
