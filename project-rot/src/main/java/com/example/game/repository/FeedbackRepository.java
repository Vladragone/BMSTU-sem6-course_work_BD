package com.example.game.repository;

import com.example.game.model.Feedback;

import java.util.Optional;

public interface FeedbackRepository {
    Feedback save(Feedback feedback);
    Optional<Feedback> findById(Long id);
}
