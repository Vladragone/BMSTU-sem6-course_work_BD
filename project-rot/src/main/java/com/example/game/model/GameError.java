package com.example.game.model;

public class GameError {
    private Long id;
    private String name;

    public GameError() {}
    public GameError(String name) { this.name = name; }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}
