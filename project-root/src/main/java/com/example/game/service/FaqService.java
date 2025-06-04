package com.example.game.service;

import com.example.game.model.Faq;
import com.example.game.repository.FaqRepository;
import com.example.game.service.interfaces.IFaqService;
import org.springframework.stereotype.Service;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;

import java.util.List;

@Service
@ConditionalOnProperty(name = "app.database", havingValue = "postgres", matchIfMissing = true)
public class FaqService implements IFaqService {

    private final FaqRepository faqRepository;

    public FaqService(FaqRepository faqRepository) {
        this.faqRepository = faqRepository;
    }

    @Override
    public List<Faq> getAllFaqs() {
        return faqRepository.findAll();
    }

    @Override
    public Faq saveFaq(Faq faq) {
        return faqRepository.save(faq);
    }
}
