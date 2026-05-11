package br.com.fiap.javaadv.blog.backend.infrastructure.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity@RequiredArgsConstructor
public class SecurityConfig {


    private final JwtAuthFilter jwtAuthFilter;
    private final UserDetailsService userDetailsService;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http ) throws Exception{
        return http.csrf( csrf -> csrf
                .ignoringRequestMatchers("/console/**")
                .disable()
        )
                .headers( headers -> headers
                        .frameOptions(frame -> frame.disable())
                )
                .authorizeHttpRequests(auth -> auth
                                .requestMatchers(
                                        "/auth/**", //em runtime
                                        "/auth/",   //em runtime
                                        "/v3/api-docs/**", // somente em dev
                                        "/swagger-ui/**",  //somente em dev
                                        "/console/**"      //somente em dev
                                ).permitAll()
                                .anyRequest().authenticated()
                        )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }


    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider =  new DaoAuthenticationProvider(this.userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authManager(){
        return new ProviderManager(this.authenticationProvider());
    }
}
