package com.nextask.nextask_app.api.DTO;

import com.nextask.nextask_app.api.entity.Tag;

public class TagDTO {
  private String id;
  private String name;
  private String color;
  
  public TagDTO() {}

  public TagDTO(Tag tag) {
    this.id = tag.getId();
    this.name = tag.getName();
    this.color = tag.getColor();
  }

  public String getID() { return id; }
  public void setId(String id) { this.id = id; }
  public String getName() { return name; }
  public void setName(String name) { this.name = name; }
  public String getColor() { return color; }
  public void setColor(String color) { this.color = color; }
 }
