package com.nextask.nextask_app.api.DTO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.nextask.nextask_app.api.entity.Card;

public class CardResponse {
  private String id;
  private String title;
  private String description;
  private LocalDateTime limitDate;
  private String storyPoints;
  private String projectId;
  private String projectName;
  private String columnId;
  private String columnName;
  private List<TagDTO> tags;
  private Integer position;
  
  public CardResponse() {}
  
  public CardResponse(Card card) {
    this.id = card.getId();
    this.title = card.getTitle();
    this.description = card.getDescription();
    this.limitDate = card.getLimitDate();
    this.storyPoints = card.getStoryPoints();
    this.position = card.getPosition();

    if (card.getProject() != null) {
      this.projectId = card.getProject().getId();
      this.projectName = card.getProject().getName();
    }
    
    if (card.getColumn() != null) {
      this.columnId = card.getColumn().getId();
      this.columnName = card.getColumn().getName();
    }

    if (card.getTags() != null) {
      List<TagDTO> tagsDTOs = card.getTags().stream().map(TagDTO::new).collect(Collectors.toList());
      this.tags = tagsDTOs;
    }
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
  
  public String getProjectId() { return projectId; }
  public void setProjectId(String projectId) { this.projectId = projectId; }
  
  public String getProjectName() { return projectName; }
  public void setProjectName(String projectName) { this.projectName = projectName; }
  
  public String getColumnId() { return columnId; }
  public void setColumnId(String columnId) { this.columnId = columnId; }
  
  public String getColumnName() { return columnName; }
  public void setColumnName(String columnName) { this.columnName = columnName; }
  
  public List<TagDTO> getTags() { return tags; }
  public void setTags(List<TagDTO> tags) { this.tags = tags; }

  public Integer getPosition() { return position; }
  public void setPosition(Integer position) { this.position = position;}
}
