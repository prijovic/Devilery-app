package com.ftn.sbnz.service.services.user;

import com.ftn.sbnz.model.models.User;
import com.ftn.sbnz.service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SaveUser {

    private final UserRepository userRepository;

    @Transactional(readOnly = false)
    public User execute(final User user) {
        return userRepository.save(user);
    }
}
