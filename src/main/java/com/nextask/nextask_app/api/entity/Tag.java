package com.nextask.nextask_app.api.entity;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tags")
public class Tag {
  @Id
  private String id;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private String color;

  @ManyToMany(mappedBy = "tags")
  private Set<Card> cards = new HashSet<>();

  // Constructors
  public Tag() {}

  public Tag(String id, String name, String color) {
    this.id = id;
    this.name = name;
    this.color = color;
  }

  // Getters and Setters
  public String getId() { return id; }
  public void setId(String id) { this.id = id; }

  public String getName() { return name; }
  public void setName(String name) { this.name = name; }

  public String getColor() { return color; }
  public void setColor(String color) { this.color = color; }

  public Set<Card> getCards() { return cards; }
}
