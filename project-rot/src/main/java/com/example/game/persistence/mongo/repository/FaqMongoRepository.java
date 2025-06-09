package com.example.game.persistence.mongo.repository;

import com.example.game.model.Faq;
import com.example.game.persistence.mongo.document.FaqDocument;
import com.example.game.repository.FaqRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class FaqMongoRepository implements FaqRepository {
    private final FaqDocumentRepository mongoRepo;

    public FaqMongoRepository(FaqDocumentRepository mongoRepo) {
        this.mongoRepo = mongoRepo;
    }

    @Override
    public List<Faq> findAll() {
        return mongoRepo.findAll().stream()
            .map(d -> {
                Faq f = new Faq();
                BeanUtils.copyProperties(d, f);
                return f;
            })
            .collect(Collectors.toList());
    }

    @Override
    public Faq save(Faq faq) {
        FaqDocument d = new FaqDocument();
        BeanUtils.copyProperties(faq, d);
        d = mongoRepo.save(d);
        Faq out = new Faq();
        BeanUtils.copyProperties(d, out);
        return out;
    }

    @Override
    public Optional<Faq> findById(Long id) {
        return mongoRepo.findById(id)
            .map(d -> {
                Faq f = new Faq();
                BeanUtils.copyProperties(d, f);
                return f;
            });
    }
}
