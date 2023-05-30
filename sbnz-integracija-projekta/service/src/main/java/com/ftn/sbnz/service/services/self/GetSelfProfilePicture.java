package com.ftn.sbnz.service.services.self;

import com.ftn.sbnz.model.models.User;
import com.ftn.sbnz.service.services.auth.GetLoggedInUser;
import com.ftn.sbnz.service.services.picture.GetPicture;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;

import java.io.IOException;


@Service
@RequiredArgsConstructor
public class GetSelfProfilePicture {
    private final GetLoggedInUser getLoggedInUser;
    private final GetPicture getPicture;

    public InputStreamResource execute() throws IOException {
        User user = getLoggedInUser.execute();
        if (user.getProfilePicture() != null) {
            return getPicture.execute(user.getProfilePicture());
        }
        return null;
    }
}
