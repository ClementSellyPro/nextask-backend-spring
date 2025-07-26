package com.nextask.nextask_app.api.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "projects")
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

  @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private List<ColumnEntity> columns = new ArrayList<>();;

  // Constructors
  public Project() {}

  public Project(String name, User user) {
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
