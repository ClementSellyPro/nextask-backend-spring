package com.nextask.nextask_app.api.repository;

import com.nextask.nextask_app.api.entity.ColumnEntity;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ColumnRepository extends JpaRepository<ColumnEntity, String> {
  List<ColumnEntity> findByProjectIdOrderByName(String projectId);
  boolean existsByName(String name);
}
