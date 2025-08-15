package com.nextask.nextask_app.api.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "cards")
public class Card {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Column(columnDefinition = "varchar(36)")
  private String id;

  @Column(nullable = false)
  private String title;

  @Column(columnDefinition = "TEXT")
  private String description;

  @Column(name = "limit_date")
  @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
  private LocalDateTime limitDate;

  @Column(name = "story_points")
  private String storyPoints;

  @Column(name = "position", nullable = false)
  private Integer position;

  @Column(name = "is_completed", nullable= false)
  private boolean isCompleted = false;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "project_id", nullable = false)
  private Project project;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "column_id", nullable = false)
  @JsonBackReference
  private ColumnEntity column;

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(
    name = "card_tags",
    joinColumns = @JoinColumn(name = "card_id"),
    inverseJoinColumns = @JoinColumn(name = "tag_id")
  )
  private Set<Tag> tags = new HashSet<>();

  @Override
  public String toString() {
    return "Card{" +
            "id='" + id + '\'' +
            ", title='" + title + '\'' +
            ", description='" + description + '\'' +
            ", limitDate='" + limitDate + '\'' +
            ", storyPoints='" + storyPoints + '\'' +
            ", position='" + position + '\'' +
            ", isCompleted='" + isCompleted + '\'' +
            '}';
  }

  // Constructors
  public Card() {}

  public Card(String id, String title, String description, LocalDateTime limitDate, String storyPoints, Integer position) {
    this.id = id;
    this.title = title;
    this.description = description;
    this.limitDate = limitDate;
    this.storyPoints = storyPoints;
    this.position = position;
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

  public boolean isCompleted() { return isCompleted; }
  public void setCompleted(boolean isCompleted) { this.isCompleted = isCompleted; }

  public Project getProject() { return project; }
  public void setProject(Project project) { this.project = project; }

  public Integer getPosition() { return position; }
  public void setPosition(Integer position) { this.position = position; }
}
