package com.nextask.nextask_app.api.DTO;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.nextask.nextask_app.api.entity.Project;

public class ProjectDTO {
  private String id;
  private String name;
  private UserDTO user;
  private List<ColumnDTO> columns;

  public ProjectDTO() {}

  public ProjectDTO(Project project) {
    this.id = project.getId();
    this.name = project.getName();
    this.user = project.getUser() != null ? new UserDTO(project.getUser()) : null;
    this.columns = project.getColumns() != null 
            ? project.getColumns().stream().map(ColumnDTO::new).collect(Collectors.toList())
            : new ArrayList<>();
  }
  
  public String getId() { return id; }
  public void setId(String id) { this.id = id; }

  public String getName() { return name; }
  public void setName(String name) { this.name = name; }

  public UserDTO getUser() { return user; }
  public void setUser(UserDTO user) { this.user = user; }

  public List<ColumnDTO> getColumns() { return columns; }
  public void setColums(List<ColumnDTO> columns) { this.columns = columns; }
}
