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

    @Bean
    UserDetailsService userDetailsService() {
        return new CustomCustomerDetailsService();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    @Bean
    SecurityFilterChain configure(HttpSecurity http) throws Exception {

        http.authenticationProvider(authenticationProvider());

        http.csrf().disable();

        http.authorizeHttpRequests(auth ->
                        auth.requestMatchers("/booking")
                                .permitAll())
                .authorizeHttpRequests(auth ->
                        auth.requestMatchers("/home").authenticated()
                                .anyRequest().permitAll()
                )
                .formLogin(login ->
                        login
                                .loginPage("/login")
                                .loginProcessingUrl("/home")
                                .usernameParameter("email")
                                .defaultSuccessUrl("/home")
                                .permitAll()
//                                .failureUrl("/login?error=True")
                )
                .logout(logout -> logout.logoutSuccessUrl("/").permitAll()
                );

        return http.build();
    }
}
