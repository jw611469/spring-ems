package com.demo.ems.entity;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "employee")
public class Employee implements UserDetails{

    public enum Role {
        ADMIN, USER, GUEST
    }

    public Employee(){
    }

    public Employee(String name,String username,String password,Role role){
        this.name = name;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    @Id
    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "role",nullable = false)
    private Role role;

    @Column(name = "name",nullable = false)
    private String name;

    @Column(name = "last_login")
    private LocalDateTime lastLogin;

    public String getUsername(){
        return username;
    }

    public String getPassword(){
        return password;
    }

    public Role getRole(){
        return role;
    }

    public String getName(){
        return name;
    }

    public LocalDateTime getLastLogin(){
        return lastLogin;
    }

    public void setRole(Role role){
        this.role = role;
    }

    public void setLastLogin(LocalDateTime localDateTime){
        this.lastLogin = localDateTime;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(this.role)
            .stream()
            .map(role-> new SimpleGrantedAuthority(role.name()))
            .collect(Collectors.toList());
    }

}
