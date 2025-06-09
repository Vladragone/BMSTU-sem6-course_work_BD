package com.example.game.persistence.postgres.repository;

import com.example.game.model.Feedback;
import com.example.game.persistence.postgres.model.FeedbackEntity;
import com.example.game.repository.FeedbackRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class FeedbackPostgresRepository implements FeedbackRepository {
    private final FeedbackEntityRepository jpaRepo;

    public FeedbackPostgresRepository(FeedbackEntityRepository jpaRepo) {
        this.jpaRepo = jpaRepo;
    }

    @Override
    public Feedback save(Feedback feedback) {
        FeedbackEntity entity = new FeedbackEntity();
        BeanUtils.copyProperties(feedback, entity);
        entity = jpaRepo.save(entity);
        Feedback out = new Feedback();
        BeanUtils.copyProperties(entity, out);
        return out;
    }

    @Override
    public Optional<Feedback> findById(Long id) {
        return jpaRepo.findById(id)
                .map(e -> {
                    Feedback f = new Feedback();
                    BeanUtils.copyProperties(e, f);
                    return f;
                });
    }
}
