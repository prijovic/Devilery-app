package com.ftn.sbnz.service.services.jwt;

import com.ftn.sbnz.model.models.User;
import com.ftn.sbnz.service.services.user.GetUserByEmail;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {
    private final GetUserByEmail getUserByEmail;

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        final User user = getUserByEmail.execute(username);

        final List<SimpleGrantedAuthority> authorities = user.getRole().getPermissions().stream().map(permission -> new SimpleGrantedAuthority(permission.name())).collect(Collectors.toList());

        return new org.springframework.security.core.userdetails.User(username, user.getPasswordHash(), authorities);
    }
}
