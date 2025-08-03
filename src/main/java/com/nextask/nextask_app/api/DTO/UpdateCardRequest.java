package com.nextask.nextask_app.api.DTO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.nextask.nextask_app.api.entity.Card;

public class UpdateCardRequest {
    private String id;
    private String title;
    private String description;
    private LocalDateTime limitDate;
    private String storyPoints;
    private String column_id;
    private List<TagDTO> tagIds;
    
    // Constructeurs, getters, setters
    public UpdateCardRequest() {}

    public UpdateCardRequest(Card card) {
        this.id = card.getId();
        this.title = card.getTitle();
        this.description = card.getDescription();
        this.limitDate = card.getLimitDate();
        this.storyPoints = card.getStoryPoints();
        this.column_id =  card.getColumn().getId();
        this.tagIds = card.getTags().stream()
            .map(TagDTO::new)
            .collect(Collectors.toList());
    }
    
    // Getters et setters...
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
    
    public String getColumn_id() { return column_id; }
    public void setColumn_id(String column_id) { this.column_id = column_id; }
    
    public List<TagDTO> getTags() { return tagIds; }
    public void setTags(List<TagDTO> tags) { this.tagIds = tags; }
}
