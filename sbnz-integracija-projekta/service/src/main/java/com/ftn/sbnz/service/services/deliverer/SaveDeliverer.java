package com.ftn.sbnz.service.services.deliverer;

import com.ftn.sbnz.model.models.Deliverer;
import com.ftn.sbnz.service.repository.DelivererRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SaveDeliverer {
    private final DelivererRepository delivererRepository;

    @Transactional(readOnly = false)
    public Deliverer execute(final Deliverer deliverer) {
        return delivererRepository.save(deliverer);
    }
}
