package com.example.game.repository;

import com.example.game.model.Faq;

import java.util.List;
import java.util.Optional;

public interface FaqRepository {
    List<Faq> findAll();
    Faq save(Faq faq);
    Optional<Faq> findById(Long id);
}
