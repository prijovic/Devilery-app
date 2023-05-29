package com.ftn.sbnz.service.services.user;

import com.ftn.sbnz.service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserExistsByEmail {

    private final UserRepository userRepository;

    public Boolean execute(final String email) {
        return userRepository.findByEmail(email).isPresent();
    }
}
