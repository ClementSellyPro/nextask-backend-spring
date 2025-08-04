package com.nextask.nextask_app.api.DTO;

public class UpdateProjectNameRequest {
  private String name;

  public UpdateProjectNameRequest() {}

  public UpdateProjectNameRequest(String name) {
    this.name = name;
  }

  public String getName() { return name; }
  public void setName(String name) { this.name = name; }

  @Override
  public String toString() {
    return "UpdateProjectNameRequest{" +
            "name='" + name + "\'" +
            "}";
  }
}
