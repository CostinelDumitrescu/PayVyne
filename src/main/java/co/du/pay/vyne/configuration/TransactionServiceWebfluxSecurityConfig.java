package co.du.pay.vyne.configuration;

import co.du.pay.vyne.repository.UserDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.server.SecurityWebFilterChain;

import java.util.List;


@Configuration
@EnableWebFluxSecurity
public class TransactionServiceWebfluxSecurityConfig {

    @Autowired
    private UserDetailsRepository userDetailsRepository;

    @Bean
    public MapReactiveUserDetailsService userDetailsService() {
        List<UserDetails> users = userDetailsRepository.getUsers();
        return new MapReactiveUserDetailsService(users);
    }

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        return http
                .csrf().disable()
                .authorizeExchange()
                .pathMatchers("/v3/api-docs/**",
                        "/swagger-ui/**",
                        "/swagger-ui.html",
                        "/webjars/**").permitAll()
                .pathMatchers("/status").permitAll()
                .pathMatchers("/").permitAll()
                .anyExchange()
                .authenticated()
                .and()
                .httpBasic()
                .and()
                .formLogin().disable()
                .build();
    }
}