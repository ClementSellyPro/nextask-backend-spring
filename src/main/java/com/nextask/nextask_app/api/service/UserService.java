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
        System.out.println("Loading user : " + username);
        return (UserDetails) userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
    }
    
    public User createUser(String username, String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        User savedUser = userRepository.save(user);

        Project defaultProject = new Project();
        defaultProject.setName("Cliquer pour ajouter un titre");
        defaultProject.setUser(savedUser);
        projectRepository.save(defaultProject);

        savedUser.setProject(defaultProject);

        return savedUser;
    }
    
    public User getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.getPrincipal() instanceof User) {
            return (User) auth.getPrincipal();
        }
        throw new RuntimeException("No authenticated user");
    }

    public Project getCurrentUserProject() {
        User currentUser = getCurrentUser();
        return currentUser.getProject();
    }
}