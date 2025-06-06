package com.example.game.mongo.service;

import com.example.game.model.Faq;
import com.example.game.mongo.model.FaqDocument;
import com.example.game.mongo.repository.FaqMongoRepository;
import com.example.game.service.interfaces.IFaqService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@ConditionalOnProperty(name = "app.database", havingValue = "mongo")
public class FaqService implements IFaqService {

    private final FaqMongoRepository faqMongoRepository;

    public FaqService(FaqMongoRepository faqMongoRepository) {
        this.faqMongoRepository = faqMongoRepository;
    }

    @Override
    public List<Faq> getAllFaqs() {
        return faqMongoRepository.findAll().stream()
            .map(this::toFaq)
            .collect(Collectors.toList());
    }

    @Override
    public Faq saveFaq(Faq faq) {
        if (faq.getId() == null) {
            faq.setId(getNextId());
        }
        FaqDocument doc = new FaqDocument(
            faq.getId(),
            faq.getQuestion(),
            faq.getAnswer(),
            faq.getUserId()
        );
        FaqDocument saved = faqMongoRepository.save(doc);
        return toFaq(saved);
    }

    private Faq toFaq(FaqDocument doc) {
        Faq faq = new Faq();
        faq.setId(doc.getId());
        faq.setQuestion(doc.getQuestion());
        faq.setAnswer(doc.getAnswer());
        faq.setUserId(doc.getUserId());
        return faq;
    }

    private Long getNextId() {
        Optional<Long> maxId = faqMongoRepository.findAll()
            .stream()
            .map(FaqDocument::getId)
            .max(Comparator.naturalOrder());
        return maxId.map(id -> id + 1).orElse(1L);
    }
}
