package com.nextask.nextask_app.api.DTO;

import java.time.LocalDateTime;
import java.util.Set;

public class CreatedCardRequest {
  private String title;
  private String description;
  private LocalDateTime limitDate;
  private String storyPoints;
  private String column_id;
  private Set<String> tagIds;

  public CreatedCardRequest() {}
    
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
  
  public Set<String> getTags() { return tagIds; }
  public void setTags(Set<String> tags) { this.tagIds = tags; }

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
