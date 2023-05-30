package com.ftn.sbnz.service.services.address;

import com.ftn.sbnz.model.models.Address;
import com.ftn.sbnz.service.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SaveAddress {
    private final AddressRepository addressRepository;

    public Address execute(Address address) {
        return addressRepository.save(address);
    }
}
