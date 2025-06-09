package com.example.game.controller;

import com.example.game.dto.GameErrorDto;
import com.example.game.model.GameError;
import com.example.game.service.api.IGameErrorService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/game-errors")
public class GameErrorController {
    private final IGameErrorService service;

    public GameErrorController(IGameErrorService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<GameErrorDto>> all() {
        List<GameErrorDto> dtos = service.getAllGameErrors().stream()
            .map(ge -> {
                GameErrorDto dto = new GameErrorDto();
                BeanUtils.copyProperties(ge, dto);
                return dto;
            })
            .toList();
        return ResponseEntity.ok(dtos);
    }
}
