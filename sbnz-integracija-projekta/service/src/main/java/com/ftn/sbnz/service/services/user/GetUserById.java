package com.ftn.sbnz.service.services.user;

import com.ftn.sbnz.model.models.User;
import com.ftn.sbnz.service.exception.UserNotFoundException;
import com.ftn.sbnz.service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GetUserById {
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public User execute(final UUID id) {
        return userRepository.findById(id).orElseThrow(UserNotFoundException::new);
    }
}
