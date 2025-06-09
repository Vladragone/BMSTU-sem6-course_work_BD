package com.example.game.persistence.mongo.repository;

import com.example.game.model.Feedback;
import com.example.game.persistence.mongo.document.FeedbackDocument;
import com.example.game.repository.FeedbackRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class FeedbackMongoRepository implements FeedbackRepository {
    private final FeedbackDocumentRepository mongoRepo;

    public FeedbackMongoRepository(FeedbackDocumentRepository mongoRepo) {
        this.mongoRepo = mongoRepo;
    }

    @Override
    public Feedback save(Feedback feedback) {
        FeedbackDocument doc = new FeedbackDocument();
        BeanUtils.copyProperties(feedback, doc);
        doc = mongoRepo.save(doc);
        Feedback out = new Feedback();
        BeanUtils.copyProperties(doc, out);
        return out;
    }

    @Override
    public Optional<Feedback> findById(Long id) {
        return mongoRepo.findById(id)
                .map(d -> {
                    Feedback f = new Feedback();
                    BeanUtils.copyProperties(d, f);
                    return f;
                });
    }
}
