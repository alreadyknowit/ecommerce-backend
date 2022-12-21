package com.example.ecommercebackend.model;

import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

@Entity
public class AppUser implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany(mappedBy = "appUser", fetch = FetchType.EAGER)
    private Set<AppUserGrantedAuthority> grantedAuthorities;
    private String password;
    private String username;
    private boolean isAccountNonExpired;
    private boolean isAccountNonLocked;
    private boolean isCredentialsNonExpired;
    private boolean isEnabled;

    public AppUser(Long id, Set<AppUserGrantedAuthority> grantedAuthorities, String password,
                   String username,
                   boolean isAccountNonExpired,
                   boolean isAccountNonLocked,
                   boolean isCredentialsNonExpired,
                   boolean isEnabled) {
        this.id = id;
        this.grantedAuthorities = grantedAuthorities;
        this.password = password;
        this.username = username;
        this.isAccountNonExpired = isAccountNonExpired;
        this.isAccountNonLocked = isAccountNonLocked;
        this.isCredentialsNonExpired = isCredentialsNonExpired;
        this.isEnabled = isEnabled;
    }
    public AppUser(){

    }


    public Long getId() {
        return id;
    }

    public Set<AppUserGrantedAuthority> getGrantedAuthorities() {
        return grantedAuthorities;
    }

    @Override
    public Collection<AppUserGrantedAuthority> getAuthorities() {
        return grantedAuthorities;
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
        return isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setGrantedAuthorities(Set<AppUserGrantedAuthority> grantedAuthorities) {
        this.grantedAuthorities = grantedAuthorities;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setAccountNonExpired(boolean accountNonExpired) {
        isAccountNonExpired = true;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        isAccountNonLocked = true;
    }

    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        isCredentialsNonExpired = true;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = true;
    }
}
