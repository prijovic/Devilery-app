package com.ftn.sbnz.service.controller;

import com.ftn.sbnz.model.models.Address;
import com.ftn.sbnz.service.services.self.GetSelfAddresses;
import com.ftn.sbnz.service.services.self.GetSelfProfilePicture;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/self")
@RequiredArgsConstructor
public class SelfController {
    private final GetSelfProfilePicture getSelfProfilePicture;
    private final GetSelfAddresses getSelfAddresses;

    @GetMapping(value = "/profile-picture", produces = MediaType.IMAGE_JPEG_VALUE)
    public InputStreamResource getProfilePicture() throws IOException {
        return getSelfProfilePicture.execute();
    }

    @GetMapping(value = "/addresses")
    public List<Address> getDeliveryAddresses() {
        return getSelfAddresses.execute();
    }

}
