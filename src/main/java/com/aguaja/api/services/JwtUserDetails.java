package com.aguaja.api.services;

import java.util.Collection;
import java.util.Objects;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.aguaja.api.domain.Seller;
import com.aguaja.api.domain.Client;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class JwtUserDetails implements UserDetails {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String username;
    private String email;
    private Boolean admin;
    private String role;

    @JsonIgnore
    private String password;

    private Collection<? extends GrantedAuthority> authorities;

    public JwtUserDetails(Long id, String username, String email, String password, Boolean admin, String role) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.admin = admin;
        this.role = role;
    }

    public static JwtUserDetails build(Client client) {

        return new JwtUserDetails(client.getId(), client.getUsername(), client.getEmail(), client.getPassword(),
                client.getAdmin(), "client");
    }

    public static JwtUserDetails build(Seller seller) {

        return new JwtUserDetails(seller.getId(), seller.getUsername(), seller.getEmail(), seller.getPassword(),
                seller.getAdmin(), "seller");
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public Boolean getAdmin() {
        return admin;
    }

    public String getRole() {
        return role;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
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

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        JwtUserDetails user = (JwtUserDetails) o;
        return Objects.equals(id, user.id);
    }
}
