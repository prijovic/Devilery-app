package com.ftn.sbnz.service.services.review;

import com.ftn.sbnz.model.models.Order;
import com.ftn.sbnz.model.models.Review;
import com.ftn.sbnz.service.dto.request.review.NewReviewRequest;
import com.ftn.sbnz.service.services.order.GetOrderById;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegisterNewReview {
    private final GetOrderById getOrderById;
    private final SaveReview saveReview;

    public Review execute(NewReviewRequest newReviewRequest) {
        Order order = getOrderById.execute(newReviewRequest.getOrderId());

        Review review = Review.builder()
                .customer(order.getCustomer())
                .deliverer(order.getDeliverer())
                .restaurant(order.getRestaurant())
                .delivererRating(newReviewRequest.getDelivererRating())
                .delivererComment(newReviewRequest.getDelivererComment())
                .restaurantRating(newReviewRequest.getRestaurantRating())
                .restaurantComment(newReviewRequest.getRestaurantComment())
                .build();

        return saveReview.execute(review);
    }
}
