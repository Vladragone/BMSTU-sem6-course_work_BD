package com.example.game.mongo.service;

import com.example.game.model.Feedback;
import com.example.game.mongo.model.FeedbackDocument;
import com.example.game.mongo.repository.FeedbackMongoRepository;
import com.example.game.service.interfaces.IFeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@ConditionalOnProperty(name = "app.database", havingValue = "mongo")
public class FeedbackService implements IFeedbackService {

    private final FeedbackMongoRepository feedbackMongoRepository;

    @Autowired
    public FeedbackService(FeedbackMongoRepository feedbackMongoRepository) {
        this.feedbackMongoRepository = feedbackMongoRepository;
    }

    @Override
    public List<Feedback> findAll() {
        return feedbackMongoRepository.findAll()
            .stream()
            .map(fd -> {
                Feedback f = new Feedback();
                f.setId(Long.parseLong(fd.getId()));
                f.setUserId(Long.parseLong(fd.getUserId()));
                f.setDescription(fd.getDescription());
                f.setRating(fd.getRating());
                f.setProblem(fd.getProblem());
                return f;
            })
            .collect(Collectors.toList());
    }

    @Override
    public Optional<Feedback> findById(Long id) {
        Optional<FeedbackDocument> doc = feedbackMongoRepository.findById(String.valueOf(id));
        if (doc.isEmpty()) return Optional.empty();
        FeedbackDocument fd = doc.get();
        Feedback f = new Feedback();
        f.setId(Long.parseLong(fd.getId()));
        f.setUserId(Long.parseLong(fd.getUserId()));
        f.setDescription(fd.getDescription());
        f.setRating(fd.getRating());
        f.setProblem(fd.getProblem());
        return Optional.of(f);
    }

    @Override
    public Feedback save(Feedback feedback) {
        FeedbackDocument fd = new FeedbackDocument();
        fd.setId(feedback.getId() == null ? null : String.valueOf(feedback.getId()));
        fd.setUserId(String.valueOf(feedback.getUserId()));
        fd.setDescription(feedback.getDescription());
        fd.setRating(feedback.getRating());
        fd.setProblem(feedback.getProblem());
        FeedbackDocument saved = feedbackMongoRepository.save(fd);
        feedback.setId(Long.parseLong(saved.getId()));
        return feedback;
    }

    @Override
    public void deleteById(Long id) {
        feedbackMongoRepository.deleteById(String.valueOf(id));
    }
}