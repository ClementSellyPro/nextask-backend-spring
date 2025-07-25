package com.nextask.nextask_app.api.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Project {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Column(columnDefinition = "varchar(36)")
  private String id;

  @Column(nullable = false)
  private String name;

  @OneToOne
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  @OneToMany(mappedBy = "projet", cascade = CascadeType.ALL)
  private List<ColumnEntity> columns;

  // Constructors
  public Project() {}

  public Project(String id, String name, User user) {
    this.id = id;
    this.name = name;
    this.user = user;
  }

  // Getters and Setters
  public String getId() { return id; }
  public void serId(String id) { this.id = id; }

  public String getName() { return name; }
  public void setName(String name) { this.name = name; }

  public User getUser() { return user; }
  public void setUser(User user) { this.user = user; }

  public List<ColumnEntity> getColumns() { return columns; }
  public void setColumns(List<ColumnEntity> columns) { this.columns = columns; }
}
