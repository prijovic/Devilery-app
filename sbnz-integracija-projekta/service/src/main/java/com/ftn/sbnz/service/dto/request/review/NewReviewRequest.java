package com.ftn.sbnz.service.dto.request.review;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class NewReviewRequest {
    @NotEmpty
    UUID orderId;
    Double delivererRating;
    String delivererComment;
    Double restaurantRating;
    String restaurantComment;
}
