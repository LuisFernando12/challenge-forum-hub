package hub.forum.api.infra.security;

import hub.forum.api.domain.user.repository.UserRepository;
import hub.forum.api.infra.token.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@Component
public class SecurityFilter extends OncePerRequestFilter {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TokenService tokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String tokenAuthorization = this.getToken(request);
        if (tokenAuthorization != null){
            String tokenSubject = this.tokenService.verifyToken(tokenAuthorization);
            var userDB = this.userRepository.findByEmail(tokenSubject);
            var authentication = new UsernamePasswordAuthenticationToken(userDB, null, userDB.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);
    }

    private String getToken(HttpServletRequest request){
        var tokenAuthorization = request.getHeader("Authorization");
        if(tokenAuthorization != null){
            return tokenAuthorization.replace("Bearer ","");
        }
        return null;
    }

}
