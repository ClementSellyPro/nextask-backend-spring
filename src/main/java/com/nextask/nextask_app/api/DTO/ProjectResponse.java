package com.nextask.nextask_app.api.DTO;

public class ProjectResponse {
  String name;

  ProjectResponse() {}

  ProjectResponse(String name) {
    this.name = name;
  }

  public String getName() { return name; }
  public void setName(String name) { this.name = name; }
}
