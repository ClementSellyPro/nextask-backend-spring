package com.nextask.nextask_app.api.entity;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User implements UserDetails {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Column(columnDefinition = "varchar(36)")
  private String id;

  @Column(unique = true, nullable = false)
  private String username;

  @Column(nullable = false)
  private String password;

@Override
public String toString() {
    return "User{" +
        "id='" + id + '\'' +
        ", username='" + username + '\'' +
        '}';
}

  // Required methods from UserDetails
  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
      return Collections.emptyList(); // No roles for now
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

  // Constructors
  public User() {}

  public User(String username, String password) {
    this.username = username;
    this.password = password;
  }

  // Getters and Setters
  public String getId() { return id; }
  public void setId(String id) { this.id = id; }

  public void setUsername(String username) { this.username = username; }
  public void setPassword(String password) { this.password = password; }
}
