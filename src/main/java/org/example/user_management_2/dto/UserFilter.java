package org.example.user_management_2.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserFilter{
  private List<Integer> ids;
  private String username;
  private String firstName;
  private String lastName;
  private String role;
  private List<Integer> departmentIds;
}
