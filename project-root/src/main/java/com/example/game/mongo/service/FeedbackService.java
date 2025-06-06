package com.example.game.mongo.service;

import com.example.game.model.Feedback;
import com.example.game.mongo.model.FeedbackDocument;
import com.example.game.mongo.repository.FeedbackMongoRepository;
import com.example.game.service.interfaces.IFeedbackService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.Optional;

@Service
@ConditionalOnProperty(name = "app.database", havingValue = "mongo")
public class FeedbackService implements IFeedbackService {

    private static final Logger logger = LoggerFactory.getLogger(FeedbackService.class);

    private final FeedbackMongoRepository feedbackMongoRepository;

    public FeedbackService(FeedbackMongoRepository feedbackMongoRepository) {
        this.feedbackMongoRepository = feedbackMongoRepository;
    }

    @Override
    public Feedback saveFeedback(Feedback feedback) {
        logger.info("Received feedback: {}", feedback);

        if (feedback.getId() == null) {
            feedback.setId(getNextId());
        }
        FeedbackDocument doc = new FeedbackDocument(
            feedback.getId(),
            feedback.getUserId(),
            feedback.getRating(),
            feedback.getProblem(),
            feedback.getDescription()
        );
        FeedbackDocument savedDoc = feedbackMongoRepository.save(doc);

        logger.info("Saved feedback with id: {}", savedDoc.getId());

        return new Feedback(
            savedDoc.getId(),
            savedDoc.getUserId(),
            savedDoc.getRating(),
            savedDoc.getProblem(),
            savedDoc.getDescription()
        );
    }

    private Long getNextId() {
        Optional<Long> maxId = feedbackMongoRepository.findAll()
            .stream()
            .map(FeedbackDocument::getId)
            .max(Comparator.naturalOrder());
        return maxId.map(id -> id + 1).orElse(1L);
    }
}
