package org.example.user_management_2.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  private String username;
  private String password;


  @Column(name = "first_name")
  private String firstName;

  @Column(name = "last_name")
  private String lastName;

  private String role;

  // Todo lưu ý sử dụng @ManyToOne cẩn thận không dễ bị N+1 query hoặc các vấn đề performance
  @ManyToOne
  @JoinColumn(name = "department_id")
  private Department department;

  // public Integer getId() {
  //   return id;
  // }
  //
  // public void setId(Integer id) {
  //   this.id = id;
  // }
  //
  // public String getUsername() {
  //   return username;
  // }
  //
  // public void setUsername(String username) {
  //   this.username = username;
  // }
  // public String getPassword() {
  //   return password;
  // }
  //
  // public void setPassword(String password) {
  //   this.password = password;
  // }
  //
  // public String getFirstName() {
  //   return firstName;
  // }
  //
  // public void setFirstName(String firstName) {
  //   this.firstName = firstName;
  // }
  //
  // public String getLastName() {
  //   return lastName;
  // }
  //
  // public void setLastName(String lastName) {
  //   this.lastName = lastName;
  // }
  //
  // public String getRole() {
  //   return role;
  // }
  //
  // public void setRole(String role) {
  //   this.role = role;
  // }
  //
  // public Department getDepartment() {
  //   return department;
  // }
  //
  // public void setDepartment(Department department) {
  //   this.department = department;
  // }

}
