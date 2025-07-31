package com.nextask.nextask_app.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.nextask.nextask_app.api.entity.Project;
import com.nextask.nextask_app.api.entity.User;
import com.nextask.nextask_app.api.repository.ProjectRepository;
import com.nextask.nextask_app.api.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class UserService implements UserDetailsService {
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProjectRepository projectRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return (UserDetails) userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
    }
    
    public User createUser(String username, String password) {
        // Vérifier si l'utilisateur existe déjà
        if (userRepository.findByUsername(username).isPresent()) {
            throw new RuntimeException("Username already exists");
        }
        
        // 1. Créer et sauvegarder l'utilisateur D'ABORD
        User user = new User(username, passwordEncoder.encode(password));
        User savedUser = userRepository.save(user);
        
        System.out.println("USER SAVED in UserService :::: " + savedUser);
        
        // 2. Créer le projet par défaut APRÈS avoir sauvé l'utilisateur
        Project defaultProject = new Project("Default Project", savedUser);
        Project savedProject = projectRepository.save(defaultProject);
        
        System.out.println("PROJECT SAVED in UserService :::: " + savedProject);
        
        return savedUser;
    }
    
    public Project getCurrentUserProject() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        
        return projectRepository.findByUserUsername(username)
            .orElseThrow(() -> new RuntimeException("No project found for current user"));
    }

    // public Project getCurrentUserProject() {
    //     User currentUser = getCurrentUser();
    //     return currentUser.getProject();
    // }
}