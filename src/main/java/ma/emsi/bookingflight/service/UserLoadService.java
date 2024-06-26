package ma.emsi.bookingflight.service;

import ma.emsi.bookingflight.entities.User;
import ma.emsi.bookingflight.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class UserLoadService implements UserDetailsService {
    @Autowired
    UserRepository u;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user= u.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        if(!user.isEnabled()){
            throw new UsernameNotFoundException("User not enabled");
        }
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                user.getRoles().stream().map(role -> new SimpleGrantedAuthority("ROLE_"+role.getName())).collect(Collectors.toList())
        );

    }

}
