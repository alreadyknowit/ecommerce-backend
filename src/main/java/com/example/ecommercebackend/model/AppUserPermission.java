package com.example.ecommercebackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
public class AppUserPermission {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String permission;

    @ManyToOne
    @JoinColumn(name = "authority_id")
    @JsonIgnore

    private AppUserGrantedAuthority authority;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AppUserGrantedAuthority getAuthority() {
        return authority;
    }

    public void setAuthority(AppUserGrantedAuthority authority) {
        this.authority = authority;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }
}
