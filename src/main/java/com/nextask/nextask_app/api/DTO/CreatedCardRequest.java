package com.nextask.nextask_app.api.DTO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.nextask.nextask_app.api.entity.Card;
import com.nextask.nextask_app.api.entity.Project;
import com.nextask.nextask_app.api.entity.Tag;

public class CreatedCardRequest {
  private String title;
  private String description;
  private LocalDateTime limitDate;
  private String storyPoints;
  private String column_id;
  private List<String> tagIds;
  private Project project;

  public CreatedCardRequest() {}

  public CreatedCardRequest(Card card) {
    this.title = card.getTitle();
    this.description = card.getDescription();
    this.limitDate = card.getLimitDate();
    this.storyPoints = card.getStoryPoints();
    this.column_id = card.getColumn().getId();
    this.tagIds = card.getTags().stream()
      .map(Tag::getId)
      .collect(Collectors.toList());
      this.project = card.getProject();
  }
    
  // Getters and Setters
  public String getTitle() { return title; }
  public void setTitle(String title) { this.title = title; }
  
  public String getDescription() { return description; }
  public void setDescription(String description) { this.description = description; }
  
  public LocalDateTime getLimitDate() { return limitDate; }
  public void setLimitDate(LocalDateTime limitDate) { this.limitDate = limitDate; }
  
  public String getStoryPoints() { return storyPoints; }
  public void setStoryPoints(String storyPoints) { this.storyPoints = storyPoints; }
  
  public String getColumn_id() { return column_id; }
  public void setColumn(String column) { this.column_id = column; }
  
  public List<String> getTags() { return tagIds; }
  public void setTags(List<String> tags) { this.tagIds = tags; }

  public Project getProject() { return project; }
  public void setProject(Project project) { this.project = project; }

  @Override
  public String toString() {
    return "CardCreateRequest{" +
            "title='" + title + '\'' +
            ", description='" + description + '\'' +
            ", limitDate=" + limitDate +
            ", storyPoints='" + storyPoints + '\'' +
            ", column_id='" + column_id + '\'' +
            ", tagIds=" + tagIds +
            '}';
  }
}
