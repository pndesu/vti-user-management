package org.example.user_management_2.dto;

import java.time.Instant;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentFilter{
  private List<Integer> ids;
  private String name;
  private Instant createdDateFrom;
  private Instant createdDateTo;
}
