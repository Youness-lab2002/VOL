package ma.emsi.bookingflight.Secutiy;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Set;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());//recuperer la liste des role de utilisateur connecter
//rederiger le user ca depent de leur role
        if (roles.contains("ROLE_admin")) {
            response.sendRedirect("/admin/members");
        } else if (roles.contains("ROLE_user")) {
            response.sendRedirect("/user/home");
        }else {
            response.sendRedirect("/login");
        }
    }
    }
