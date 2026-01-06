package org.example.user_management_2.entity;

import java.time.Instant;

import jakarta.persistence.*;

@Entity
@Table(name = "departments")
public class Department{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  private String name;

  @Column(name = "created_date")
  private Instant createdDate;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Instant getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(Instant createdDate) {
    this.createdDate = createdDate;
  }
  
}
