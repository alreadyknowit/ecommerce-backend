package com.example.ecommercebackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Set;

@Entity
public class AppUserGrantedAuthority implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String authority;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private AppUser appUser;

    @OneToMany(mappedBy = "authority", fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<AppUserPermission> permissions;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }

    public Set<AppUserPermission> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<AppUserPermission> permissions) {
        this.permissions = permissions;
    }

    @Override
    public String getAuthority() {
        return authority;
    }
}
