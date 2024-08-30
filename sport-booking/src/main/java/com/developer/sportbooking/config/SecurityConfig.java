package com.developer.sportbooking.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final UserDetailsService userDetailsService;

    public SecurityConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        System.out.println(passwordEncoder().encode("123456"));
        return authProvider;
    }

    @Bean
    SecurityFilterChain configure(HttpSecurity http) throws Exception {

        http.authenticationProvider(authenticationProvider());

        http.csrf().disable();

        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/booking").permitAll()   // Allow access to /booking without authentication
                        .requestMatchers("/account").authenticated() // Require authentication for /account
                        .anyRequest().permitAll()                  // Permit all other requests
                )
                .formLogin(login -> login
                        .loginPage("/login")                        // Custom login page
                        .loginProcessingUrl("/perform_login")       // Form action URL for login processing
                        .usernameParameter("username")              // Name of the username field in the login form
                        .passwordParameter("password")              // Name of the password field in the login form
                        .defaultSuccessUrl("/homepage", true)       // Redirect to this page after successful login
                        .permitAll()                                // Allow access to the login page for everyone
                        .failureUrl("/login?error=True")            // Redirect to login page with error parameter on failure
                )
                .logout(logout -> logout
                        .logoutSuccessUrl("/")                      // Redirect to this page after logout
                        .permitAll()                                // Allow everyone to access the logout page
                );

        return http.build();
    }
}
