package boguslavskii.leonid.carrouselcustomercounter.service;

import boguslavskii.leonid.carrouselcustomercounter.dto.MyUserDetails;
import boguslavskii.leonid.carrouselcustomercounter.entities.User;
import boguslavskii.leonid.carrouselcustomercounter.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("Пользователь не найден: " + username);
        }
        return new MyUserDetails(user.get());
    }
}
