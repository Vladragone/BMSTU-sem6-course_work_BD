package com.example.game.mongo.service;

import com.example.game.model.Faq;
import com.example.game.mongo.model.FaqDocument;
import com.example.game.mongo.repository.FaqMongoRepository;
import com.example.game.service.interfaces.IFaqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@ConditionalOnProperty(name = "app.database", havingValue = "mongo")
public class FaqService implements IFaqService {

    private final FaqMongoRepository faqMongoRepository;

    @Autowired
    public FaqService(FaqMongoRepository faqMongoRepository) {
        this.faqMongoRepository = faqMongoRepository;
    }

    @Override
    public List<Faq> findAll() {
        return faqMongoRepository.findAll()
            .stream()
            .map(fd -> {
                Faq f = new Faq();
                f.setId(Long.parseLong(fd.getId()));
                f.setQuestion(fd.getQuestion());
                f.setAnswer(fd.getAnswer());
                f.setUserId(Long.parseLong(fd.getUserId()));
                return f;
            })
            .collect(Collectors.toList());
    }

    @Override
    public Optional<Faq> findById(Long id) {
        Optional<FaqDocument> doc = faqMongoRepository.findById(String.valueOf(id));
        if (doc.isEmpty()) return Optional.empty();
        FaqDocument fd = doc.get();
        Faq f = new Faq();
        f.setId(Long.parseLong(fd.getId()));
        f.setQuestion(fd.getQuestion());
        f.setAnswer(fd.getAnswer());
        f.setUserId(Long.parseLong(fd.getUserId()));
        return Optional.of(f);
    }

    @Override
    public Faq save(Faq faq) {
        FaqDocument fd = new FaqDocument();
        fd.setId(faq.getId() == null ? null : String.valueOf(faq.getId()));
        fd.setQuestion(faq.getQuestion());
        fd.setAnswer(faq.getAnswer());
        fd.setUserId(String.valueOf(faq.getUserId()));
        FaqDocument saved = faqMongoRepository.save(fd);
        faq.setId(Long.parseLong(saved.getId()));
        return faq;
    }

    @Override
    public void deleteById(Long id) {
        faqMongoRepository.deleteById(String.valueOf(id));
    }
}