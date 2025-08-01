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
        if (userRepository.findByUsername(username).isPresent()) {
            throw new RuntimeException("Username already exists");
        }
        
        User user = new User(username, passwordEncoder.encode(password));
        User savedUser = userRepository.save(user);
        
        Project defaultProject = new Project("Default Project", savedUser);
        projectRepository.save(defaultProject);
        
        return savedUser;
    }
    
    public Project getCurrentUserProject() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        
        return projectRepository.findByUserUsername(username)
            .orElseThrow(() -> new RuntimeException("No project found for current user"));
    }
}