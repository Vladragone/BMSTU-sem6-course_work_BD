package com.example.game.service.impl;

import com.example.game.model.Faq;
import com.example.game.repository.FaqRepository;
import com.example.game.service.api.IFaqService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FaqServiceImpl implements IFaqService {
    private final FaqRepository repo;

    public FaqServiceImpl(FaqRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<Faq> getAllFaqs() {
        return repo.findAll();
    }

    @Override
    public Faq saveFaq(Faq faq) {
        return repo.save(faq);
    }
}
