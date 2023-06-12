package com.ftn.sbnz.service.services.order;

import com.ftn.sbnz.model.models.OrderStatus;
import com.ftn.sbnz.model.models.User;
import com.ftn.sbnz.service.converter.OrderConverter;
import com.ftn.sbnz.service.dto.response.OrderResponse;
import com.ftn.sbnz.service.repository.OrderRepository;
import com.ftn.sbnz.service.services.auth.GetLoggedInUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GetUsersOrderHistory {
    private final OrderRepository orderRepository;
    private final GetLoggedInUser getLoggedInUser;

    public List<OrderResponse> execute() {
        User user = getLoggedInUser.execute();
        return orderRepository.findAllByCustomerAndStatusIn(user, Arrays.asList(OrderStatus.DELIVERED, OrderStatus.REJECTED, OrderStatus.UNSUCCESSFUL_DELIVERY))
                .stream()
                .map(OrderConverter::toOrderResponse)
                .collect(Collectors.toList());
    }
}
