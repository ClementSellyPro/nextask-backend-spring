package com.nextask.nextask_app.api.DTO;

import com.nextask.nextask_app.api.entity.User;

public class UserDTO {
  private String id;
  private String username;

  public UserDTO() {}

  public UserDTO(User user) {
    this.id = user.getId();
    this.username = user.getUsername();
  }

  public String getId() { return id; }
  public void setId(String id) { this.id = id; }

  public String getUsername() { return username; }
  public void setUsername(String username) { this.username = username; }
}