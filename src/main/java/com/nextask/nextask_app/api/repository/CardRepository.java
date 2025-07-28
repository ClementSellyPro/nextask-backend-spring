package com.nextask.nextask_app.api.repository;

import com.nextask.nextask_app.api.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
// import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CardRepository extends JpaRepository<Card, String> {
  List<Card> findByColumnIdOrderByTitle(String columnId);
  List<Card> findByProjectId(String projectId);
  List<Card> findByColumnId(String columnId);

  @Query("SELECT c FROM Card c JOIN c.tags t WHERE t.id = :tagId")
    List<Card> findByTagId(@Param("tagId") String tagId);
}