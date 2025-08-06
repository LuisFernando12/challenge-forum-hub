package hub.forum.api.infra.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    @Autowired
    private SecurityFilter securityFilter;
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
      return   httpSecurity
              .csrf(AbstractHttpConfigurer::disable)
              .sessionManagement(session->
                      session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
              )
              .authorizeHttpRequests(authorize ->
                      authorize
                        .requestMatchers(HttpMethod.POST, "/users", "/auth/login")
                        .permitAll()
                        .requestMatchers("/v3/api-docs/**", "/swagger-ui.html", "/swagger-ui/**")
                        .permitAll()
                        .anyRequest().authenticated()
              )
              .addFilterBefore(this.securityFilter, UsernamePasswordAuthenticationFilter.class)
              .build();
    }
}
