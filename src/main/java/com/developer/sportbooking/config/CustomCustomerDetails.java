package com.developer.sportbooking.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Setter
@Getter
public class CustomCustomerDetails implements UserDetails {

    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    public CustomCustomerDetails(String firstName, String lastName, String phone, String email, String password,Collection<? extends GrantedAuthority> authorities) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
    }


    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
