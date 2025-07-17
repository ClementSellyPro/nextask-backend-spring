package com.nextask.nextask_app.api.repository;

import com.nextask.nextask_app.api.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends JpaRepository<Tag, String> {
  
}
