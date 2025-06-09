package com.example.game.persistence.mongo.repository;

import com.example.game.model.GameError;
import com.example.game.persistence.mongo.document.GameErrorDocument;
import com.example.game.repository.GameErrorRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class GameErrorMongoRepository implements GameErrorRepository {
    private final GameErrorDocumentRepository mongoRepo;

    public GameErrorMongoRepository(GameErrorDocumentRepository mongoRepo) {
        this.mongoRepo = mongoRepo;
    }

    @Override
    public List<GameError> findAll() {
        return mongoRepo.findAll().stream()
            .map(d -> {
                GameError ge = new GameError();
                BeanUtils.copyProperties(d, ge);
                return ge;
            })
            .collect(Collectors.toList());
    }
}
