package com.ftn.sbnz.service.controller;

import com.ftn.sbnz.service.dto.request.review.NewReviewRequest;
import com.ftn.sbnz.service.services.review.RegisterNewReview;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/review")
@RequiredArgsConstructor
public class ReviewController {
    private final RegisterNewReview registerNewReview;

    @PostMapping
    public void createReview(@RequestBody NewReviewRequest newReviewRequest) {
        registerNewReview.execute(newReviewRequest);
    }
}
