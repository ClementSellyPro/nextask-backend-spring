package com.nextask.nextask_app.api.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "cards")
public class Card {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(columnDefinition = "varchar(36)")
  private String id;

  @Column(nullable = false)
  private String title;

  @Column(columnDefinition = "TEXT")
  private String description;

  @Column(name = "limit_date")
  private LocalDateTime limitDate;

  @Column(name = "story_points")
  private String storyPoints;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "column_id", nullable = false)
  private ColumnEntity column;

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(
    name = "card_tags",
    joinColumns = @JoinColumn(name = "card_id"),
    inverseJoinColumns = @JoinColumn(name = "tag_id")
  )
  private Set<Tag> tags = new HashSet<>();

  // Constructors
  public Card() {}

  public Card(String id, String title, String description, LocalDateTime limitDate, String storyPoints) {
    this.id = id;
    this.title = title;
    this.description = description;
    this.limitDate = limitDate;
    this.storyPoints = storyPoints;
  }

  // Getters and Setters
  public String getId() { return id; }
  public void setId(String id) { this.id = id; }
  
  public String getTitle() { return title; }
  public void setTitle(String title) { this.title = title; }
  
  public String getDescription() { return description; }
  public void setDescription(String description) { this.description = description; }
  
  public LocalDateTime getLimitDate() { return limitDate; }
  public void setLimitDate(LocalDateTime limitDate) { this.limitDate = limitDate; }
  
  public String getStoryPoints() { return storyPoints; }
  public void setStoryPoints(String storyPoints) { this.storyPoints = storyPoints; }
  
  public ColumnEntity getColumn() { return column; }
  public void setColumn(ColumnEntity column) { this.column = column; }
  
  public Set<Tag> getTags() { return tags; }
  public void setTags(Set<Tag> tags) { this.tags = tags; }
}
