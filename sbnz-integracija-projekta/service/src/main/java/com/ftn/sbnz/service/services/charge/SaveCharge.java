package com.ftn.sbnz.service.services.charge;

import com.ftn.sbnz.model.models.Charge;
import com.ftn.sbnz.service.repository.ChargeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SaveCharge {
    private final ChargeRepository chargeRepository;

    public Charge execute(Charge charge) {
        return chargeRepository.save(charge);
    }
}
