package com.example.game.persistence.postgres.repository;

import com.example.game.model.Faq;
import com.example.game.persistence.postgres.model.FaqEntity;
import com.example.game.repository.FaqRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class FaqPostgresRepository implements FaqRepository {
    private final FaqEntityRepository jpaRepo;

    public FaqPostgresRepository(FaqEntityRepository jpaRepo) {
        this.jpaRepo = jpaRepo;
    }

    @Override
    public List<Faq> findAll() {
        return jpaRepo.findAll().stream()
            .map(e -> {
                Faq f = new Faq();
                BeanUtils.copyProperties(e, f);
                return f;
            })
            .collect(Collectors.toList());
    }

    @Override
    public Faq save(Faq faq) {
        FaqEntity e = new FaqEntity();
        BeanUtils.copyProperties(faq, e);
        e = jpaRepo.save(e);
        Faq out = new Faq();
        BeanUtils.copyProperties(e, out);
        return out;
    }

    @Override
    public Optional<Faq> findById(Long id) {
        return jpaRepo.findById(id)
            .map(e -> {
                Faq f = new Faq();
                BeanUtils.copyProperties(e, f);
                return f;
            });
    }
}
