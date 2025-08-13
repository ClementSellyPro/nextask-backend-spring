package com.nextask.nextask_app.api.repository;

import com.nextask.nextask_app.api.entity.Card;
import com.nextask.nextask_app.api.entity.ColumnEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CardRepository extends JpaRepository<Card, String> {
  List<Card> findByColumnIdOrderByTitle(String columnId);
  List<Card> findByColumnIdOrderByPosition(String columnId);
  List<Card> findByProjectId(String projectId);
  List<Card> findByProjectIdOrderByPositionAsc(String projectId);
  List<Card> findByColumnId(String columnId);

  @Query("SELECT c FROM Card c JOIN c.tags t WHERE c.id = :tagId")
  List<Card> findByTagId(@Param("tagId") String tagId);

  @Modifying
  @Query("UPDATE Card c SET c.position = :newPosition WHERE c.id = :cardId")
  void updateCardPosition(@Param("cardId") String cardId, @Param("newPosition") Integer newPosition);

  @Modifying
  @Query("UPDATE Card c SET c.position = c.position + 1000 " +
          "WHERE c.column.id = :columnId " +
          "AND c.position >= :startPosition " +
          "AND c.position < :endPosition")
  void incrementPositionsBetween(@Param("columnId") String columnId, 
                                  @Param("startPosition") Integer startPosition, 
                                  @Param("endPosition") Integer endPosition);
  
  @Modifying
  @Query("UPDATE Card c SET c.position = c.position - 1000 " +
          "WHERE c.column.id = :columnId " +
          "AND c.position > :startPosition " +
          "AND c.position <= :endPosition")
  void decrementPositionsBetween(@Param("columnId") String columnId, 
                                  @Param("startPosition") Integer startPosition, 
                                  @Param("endPosition") Integer endPosition);
  
  // Méthode utile pour obtenir la position maximale
  @Query("SELECT COALESCE(MAX(c.position), 0) FROM Card c WHERE c.column.id = :columnId")
  Integer getMaxPositionInColumn(@Param("columnId") String columnId);

  @Modifying
  @Query("UPDATE Card c SET c.position = c.position - 1000 " +
          "WHERE c.column.id = :columnId AND c.position > :position")
  void decrementPositionsAfter(@Param("columnId") String columnId, @Param("position") Integer position);
  
  @Modifying
  @Query("UPDATE Card c SET c.position = c.position + 1000 " +
          "WHERE c.column.id = :columnId AND c.position > :position")
  void incrementPositionsAfter(@Param("columnId") String columnId, @Param("position") Integer position);
  
  @Modifying
  @Query("UPDATE Card c SET c.column = :newColumn, c.position = :newPosition WHERE c.id = :cardId")
  void updateTaskColumnAndPosition(@Param("cardId") String cardId, 
                                    @Param("newColumn") ColumnEntity newColumn, 
                                    @Param("newPosition") Integer newPosition);
}