package com.nextask.nextask_app.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nextask.nextask_app.api.DTO.ProjectDTO;
import com.nextask.nextask_app.api.DTO.UpdateProjectNameRequest;
import com.nextask.nextask_app.api.entity.Project;
import com.nextask.nextask_app.api.service.ProjectService;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
@RequestMapping("/api/projects")
public class ProjectController {
  
  @Autowired
  private ProjectService projectService;

  @GetMapping
  public ResponseEntity<ProjectDTO> getCurrentProject() {
    try {
      Project project = projectService.getCurrentUserProject();
      ProjectDTO projectDTO = new ProjectDTO(project);
      return ResponseEntity.ok(projectDTO);
    } catch (RuntimeException e) {
      return ResponseEntity.notFound().build();
    }
  }

  @PutMapping("/name")
  public ResponseEntity<ProjectDTO> updateProjectName(@RequestBody UpdateProjectNameRequest request) {
    try {
      Project updatedProject = projectService.updateProjectName(request.getName());
      ProjectDTO projectDTO = new ProjectDTO(updatedProject);
      return ResponseEntity.ok(projectDTO);
    } catch (RuntimeException e) {
      return ResponseEntity.notFound().build();
    }
  }
}
