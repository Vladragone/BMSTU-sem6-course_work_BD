package com.example.game.controller;

import com.example.game.dto.FaqDto;
import com.example.game.model.Faq;
import com.example.game.service.api.IFaqService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/faq")
public class FaqController {
    private final IFaqService service;

    public FaqController(IFaqService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<FaqDto>> all() {
        List<FaqDto> dtos = service.getAllFaqs().stream().map(f -> {
            FaqDto dto = new FaqDto();
            BeanUtils.copyProperties(f, dto);
            return dto;
        }).toList();
        return ResponseEntity.ok(dtos);
    }

    @PostMapping
    public ResponseEntity<FaqDto> create(@RequestBody FaqDto dto) {
        Faq model = new Faq();
        BeanUtils.copyProperties(dto, model);
        Faq saved = service.saveFaq(model);
        FaqDto out = new FaqDto();
        BeanUtils.copyProperties(saved, out);
        return ResponseEntity
            .created(URI.create("/api/faq/" + out.getId()))
            .body(out);
    }
}
