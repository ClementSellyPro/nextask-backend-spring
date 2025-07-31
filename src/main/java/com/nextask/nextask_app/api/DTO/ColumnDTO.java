package com.nextask.nextask_app.api.DTO;

import com.nextask.nextask_app.api.entity.ColumnEntity;

public class ColumnDTO {
  private String id;
  private String name;
  private String color;
  private String projectId;

  public ColumnDTO() {}

  public ColumnDTO(ColumnEntity column) {
    this.id = column.getId();
    this.name = column.getName();
    this.color = column.getColor();
    this.projectId = column.getProject() != null ? column.getProject().getId() : null;
  }

  // Getters
  public String getId() { return id; }
  public String getName() { return name; }
  public String getColor() { return color; }
  public String getProjectId() { return projectId; }

  // Setters 
  public void setId(String id) { this.id = id; }
  public void setName(String name) { this.name = name; }
  public void setColor(String color) { this.color = color; }
  public void setProjectId(String projectId) { this.projectId = projectId; }

  @Override
  public String toString() {
    return "ColumnDTO{" +
            "id='" + id + '\'' +
            ", name='" + name + '\'' +
            ", color='" + color + '\'' +
            ", projectId='" + projectId + '\'' +
            '}';
  }
}
