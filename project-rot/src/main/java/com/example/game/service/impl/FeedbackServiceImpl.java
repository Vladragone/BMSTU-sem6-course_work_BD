package com.example.game.service.impl;

import com.example.game.model.Feedback;
import com.example.game.repository.FeedbackRepository;
import com.example.game.service.api.IFeedbackService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class FeedbackServiceImpl implements IFeedbackService {
    private static final Logger logger = LoggerFactory.getLogger(FeedbackServiceImpl.class);
    private final FeedbackRepository repo;

    public FeedbackServiceImpl(FeedbackRepository repo) {
        this.repo = repo;
    }

    @Override
    public Feedback saveFeedback(Feedback feedback) {
        logger.info("Received feedback: {}", feedback);
        Feedback saved = repo.save(feedback);
        logger.info("Saved feedback with id: {}", saved.getId());
        return saved;
    }
}
