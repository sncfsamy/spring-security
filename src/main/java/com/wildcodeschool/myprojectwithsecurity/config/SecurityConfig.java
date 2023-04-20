package com.wildcodeschool.myprojectwithsecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((authz) -> {
            try {
                authz.requestMatchers("/").permitAll()
                .requestMatchers("/avengers/assemble").hasRole("CHAMPION")
                .requestMatchers("/secret-bases").hasRole("DIRECTOR")
                .anyRequest().denyAll()
                .and().formLogin(withDefaults())
                .httpBasic(withDefaults());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        return http.build();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

         UserDetails steve = User
            .withUsername("Steve")
            .password(encoder.encode("motdepasse"))
            .roles("CHAMPION")
            .build();

        UserDetails nick = User
            .withUsername("Nick")
            .password(encoder.encode("flerken"))
            .roles("DIRECTOR")
            .build();

            return new InMemoryUserDetailsManager(List.of(steve, nick));
    }
}
