package com.nextask.nextask_app.api.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "columns")
public class ColumnEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Column(columnDefinition = "varchar(36)")
  private String id;
  
  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private String color;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "project_id", nullable = false)
  private Project project;

  @OneToMany(mappedBy = "column", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JsonManagedReference
  private List<Card> cards = new ArrayList<>();

  // Constructors
  public ColumnEntity() {}
  
  public ColumnEntity(String id, String name, String color) {
    this.id = id;
    this.name = name;
    this.color = color;
  }

  public ColumnEntity(String name, Project project) {
    this.name = name;
    this.project = project;
  }

  // Getters and Setters
  public String getId() { return id; }
  public void setId(String id) { this.id = id; }
  
  public String getName() { return name; }
  public void setName(String name) { this.name = name; }
  
  public String getColor() { return color; }
  public void setColor(String color) { this.color = color; }
  
  public List<Card> getCards() { return cards; }
  public void setCards(List<Card> cards) { this.cards = cards; }

  public Project getProject() { return project; }
  public void setProject(Project project) { this.project = project; } 
}
