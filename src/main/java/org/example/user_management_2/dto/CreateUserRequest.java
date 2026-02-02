package org.example.user_management_2.dto;

import org.example.user_management_2.entity.Department;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CreateUserRequest{
  private String username;
  private String password;
  private String firstName;
  private String lastName;
  private Department department;
}
