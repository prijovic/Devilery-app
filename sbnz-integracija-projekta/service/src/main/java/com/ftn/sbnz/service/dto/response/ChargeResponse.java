package com.ftn.sbnz.service.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChargeResponse {
    Double productsCost;
    Double servicesFee;
    Double deliveryFee;
    Double userDiscount;
    Double discountValue;
    Double total;
}
