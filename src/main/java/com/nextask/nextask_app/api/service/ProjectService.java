package com.nextask.nextask_app.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nextask.nextask_app.api.entity.Project;
import com.nextask.nextask_app.api.repository.ProjectRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ProjectService {
    
    @Autowired
    private ProjectRepository projectRepository;
    
    @Autowired
    private UserService userService;
    
    public Project getCurrentUserProject() {
        return userService.getCurrentUserProject();
    }
    
    public Project updateProjectName(String newName) {
        Project project = getCurrentUserProject();
        project.setName(newName);
        return projectRepository.save(project);
    }
}