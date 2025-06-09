package com.example.game.repository;

import com.example.game.model.GameError;

import java.util.List;

public interface GameErrorRepository {
    List<GameError> findAll();
}
