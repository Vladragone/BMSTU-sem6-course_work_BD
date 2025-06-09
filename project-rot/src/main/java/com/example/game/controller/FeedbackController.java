package com.example.game.controller;

import com.example.game.dto.FeedbackDto;
import com.example.game.model.Feedback;
import com.example.game.service.api.IFeedbackService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/feedback")
public class FeedbackController {
    private final IFeedbackService service;

    public FeedbackController(IFeedbackService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<FeedbackDto> addFeedback(@RequestBody FeedbackDto dto) {
        Feedback model = new Feedback();
        BeanUtils.copyProperties(dto, model);
        Feedback saved = service.saveFeedback(model);
        FeedbackDto out = new FeedbackDto();
        BeanUtils.copyProperties(saved, out);
        return ResponseEntity
            .created(URI.create("/api/feedback/" + out.getId()))
            .body(out);
    }
}
