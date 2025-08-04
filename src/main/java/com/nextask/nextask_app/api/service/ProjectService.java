package com.nextask.nextask_app.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.nextask.nextask_app.api.entity.Project;
import com.nextask.nextask_app.api.repository.ProjectRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ProjectService {
    
    @Autowired
    private ProjectRepository projectRepository;
    
    public Project getCurrentUserProject() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        
        return projectRepository.findByUserUsername(username)
                .orElseThrow(() -> new RuntimeException("No project found for current user"));
    }
    
    public Project updateProjectName(String newName) {
        Project project = getCurrentUserProject();
        project.setName(newName);
        return projectRepository.save(project);
    }
}