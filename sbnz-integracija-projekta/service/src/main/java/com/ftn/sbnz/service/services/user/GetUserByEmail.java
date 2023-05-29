package com.ftn.sbnz.service.services.user;

import com.ftn.sbnz.model.models.User;
import com.ftn.sbnz.service.exception.UserNotFoundException;
import com.ftn.sbnz.service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class GetUserByEmail {
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public User execute(final String email) {
        return userRepository.findByEmail(email).orElseThrow(UserNotFoundException::new);
    }
}
