package com.ftn.sbnz.service.services.review;

import com.ftn.sbnz.model.models.Review;
import com.ftn.sbnz.service.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SaveReview {
    private final ReviewRepository reviewRepository;

    public Review execute(Review review) {
        return reviewRepository.save(review);
    }
}
