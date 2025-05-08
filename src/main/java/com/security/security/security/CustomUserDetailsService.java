package com.security.security.security;

import com.security.security.entity.MasterUsers;
import com.security.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        MasterUsers user = userRepository.findByUserEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));

        return new User(
                user.getUserName(),               // username
                user.getPassword(),              // password (should be encoded)
                user.getIsActive(),              // enabled
                true,                            // accountNonExpired
                true,                            // credentialsNonExpired
                true,                            // accountNonLocked
                Collections.emptyList()          // authorities (no roles for now)
        );
    }
}
