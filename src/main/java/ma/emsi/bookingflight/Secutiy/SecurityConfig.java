package ma.emsi.bookingflight.Secutiy;

import ma.emsi.bookingflight.service.UserLoadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final CustomAuthenticationSuccessHandler successHandler;
    private final UserLoadService userLoadService;
    @Autowired
    public SecurityConfig(CustomAuthenticationSuccessHandler successHandler, UserLoadService userLoadService) {
        this.successHandler = successHandler;
        this.userLoadService = userLoadService;
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.authorizeHttpRequests((request -> request
                        .requestMatchers("/user/vols/Signup","/user/vols/saveUser").permitAll()
                        .requestMatchers("/user/**").authenticated()
                        .requestMatchers("/admin/**").hasAuthority("ROLE_admin")
                        .requestMatchers("/user/home").permitAll()
                        .requestMatchers("vols/hi").hasAuthority("ROLE_user")
                        .anyRequest().permitAll())


                ).formLogin((form) -> form.loginProcessingUrl("/perform_login").successHandler(successHandler).permitAll());
        return http.build();
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
