package ma.emsi.bookingflight.Secutiy;

import ma.emsi.bookingflight.service.UserLoadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AuthenticatiomConfig {
    @Autowired
    UserLoadService userLoadService;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(userLoadService).passwordEncoder(passwordEncoder);
    }

}
