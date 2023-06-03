package com.ftn.sbnz.service.services.self;

import com.ftn.sbnz.model.models.Address;
import com.ftn.sbnz.model.models.User;
import com.ftn.sbnz.service.services.auth.GetLoggedInUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetSelfAddresses {
    private final GetLoggedInUser getLoggedInUser;

    public List<Address> execute() {
        User user = getLoggedInUser.execute();
        return user.getDeliveryAddresses();
    }
}
