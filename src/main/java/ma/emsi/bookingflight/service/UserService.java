package ma.emsi.bookingflight.service;

import ma.emsi.bookingflight.entities.User;
import ma.emsi.bookingflight.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository u;
    public User usersave(User user){
        return u.save(user);
    }
}
