package com.example.game.service.api;

import com.example.game.dto.RatingResponse;

public interface IRatingService {
    RatingResponse getSortedRatingAndRank(String token, String sortBy);
}