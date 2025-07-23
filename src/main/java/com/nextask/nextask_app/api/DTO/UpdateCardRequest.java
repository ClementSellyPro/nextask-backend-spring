package com.nextask.nextask_app.api.DTO;

import java.time.LocalDateTime;
import java.util.Set;

public class UpdateCardRequest {
    private String title;
    private String description;
    private LocalDateTime limitDate;
    private String storyPoints;
    private String color;
    private String column_id;
    private Set<String> tags;
    
    // Constructeurs, getters, setters
    public UpdateCardRequest() {}
    
    // Getters et setters...
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public LocalDateTime getLimitDate() { return limitDate; }
    public void setLimitDate(LocalDateTime limitDate) { this.limitDate = limitDate; }
    
    public String getStoryPoints() { return storyPoints; }
    public void setStoryPoints(String storyPoints) { this.storyPoints = storyPoints; }
    
    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }
    
    public String getColumn_id() { return column_id; }
    public void setColumn_id(String column_id) { this.column_id = column_id; }
    
    public Set<String> getTags() { return tags; }
    public void setTags(Set<String> tags) { this.tags = tags; }
}
