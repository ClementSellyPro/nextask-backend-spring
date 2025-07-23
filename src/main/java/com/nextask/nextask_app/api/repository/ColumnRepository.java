package com.nextask.nextask_app.api.repository;

import com.nextask.nextask_app.api.entity.ColumnEntity;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
// import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ColumnRepository extends JpaRepository<ColumnEntity, String> {

  @Query("SELECT c FROM ColumnEntity c LEFT JOIN FETCH c.cards WHERE c.id = :id")
  Optional<ColumnEntity> findByIdWithCards(String id);
    
  boolean existsByName(String name);
}
