package com.ftn.sbnz.service.converter;

import com.ftn.sbnz.model.models.Charge;
import com.ftn.sbnz.service.dto.response.ChargeResponse;

public class ChargeConverter {
    public static ChargeResponse toChargeResponse(Charge charge) {
        return ChargeResponse.builder()
                .deliveryFee(charge.getDeliveryFee())
                .productsCost(charge.getProductsCost())
                .total(charge.getTotal())
                .discountValue(charge.getDiscountValue())
                .userDiscount(charge.getUserDiscount())
                .servicesFee(charge.getServicesFee())
                .build();
    }
}
