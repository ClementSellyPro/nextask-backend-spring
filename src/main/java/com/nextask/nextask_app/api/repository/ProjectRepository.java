package com.nextask.nextask_app.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nextask.nextask_app.api.entity.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, String> {
    Optional<Project> findByUserId(String userId);
}
