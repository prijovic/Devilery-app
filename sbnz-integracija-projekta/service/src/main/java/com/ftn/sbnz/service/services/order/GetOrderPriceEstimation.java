package com.ftn.sbnz.service.services.order;

import com.ftn.sbnz.model.models.MenuItem;
import com.ftn.sbnz.model.models.Order;
import com.ftn.sbnz.model.models.User;
import com.ftn.sbnz.service.converter.ChargeConverter;
import com.ftn.sbnz.service.dto.request.order.GetPriceEstimationRequest;
import com.ftn.sbnz.service.dto.response.ChargeResponse;
import com.ftn.sbnz.service.services.auth.GetLoggedInUser;
import com.ftn.sbnz.service.services.menuItem.GetMenuItemsById;
import com.ftn.sbnz.service.services.user.CalculateDiscount;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetOrderPriceEstimation {
    private final GetLoggedInUser getLoggedInUser;
    private final GetMenuItemsById getMenuItemsById;
    private final CalculateDiscount calculateDiscount;
    private final CalculateOrderPrice calculateOrderPrice;

    public ChargeResponse execute(GetPriceEstimationRequest getPriceEstimationRequest) {
        User customer = getLoggedInUser.execute();
        calculateDiscount.execute(customer);

        List<MenuItem> menuItems = getMenuItemsById.execute(getPriceEstimationRequest.getItemIds());
        Order order = Order.builder()
                .customer(customer)
                .items(menuItems)
                .deliveryDistance(getPriceEstimationRequest.getDeliveryDistance())
                .build();

        calculateOrderPrice.execute(order);

        return ChargeConverter.toChargeResponse(order.getCharge());
    }
}
