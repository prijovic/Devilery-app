package com.ftn.sbnz.service.services.address;

import com.ftn.sbnz.model.models.Address;
import com.ftn.sbnz.service.exception.AddressNotFoundException;
import com.ftn.sbnz.service.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GetAddressById {
    private final AddressRepository addressRepository;

    public Address execute(UUID id) {
        return addressRepository.findById(id).orElseThrow(AddressNotFoundException::new);
    }
}
