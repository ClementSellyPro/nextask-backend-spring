package com.nextask.nextask_app.api.repository;

import com.nextask.nextask_app.api.entity.Project;
import com.nextask.nextask_app.api.entity.Tag;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends JpaRepository<Tag, String> {
  boolean existsByName(String name);
  boolean existsByNameAndProject(String name, Project project);

  List<Tag> findByProject(Project project);
}
