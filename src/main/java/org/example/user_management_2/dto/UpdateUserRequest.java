package org.example.user_management_2.dto;

import org.example.user_management_2.entity.Department;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class UpdateUserRequest{
  private String password;
  private String firstName;
  private String lastName;
  private Integer departmentId;
}
