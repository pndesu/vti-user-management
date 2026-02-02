package org.example.user_management_2.service;

import org.example.user_management_2.dto.UpdateDepartmentRequest;
import org.example.user_management_2.entity.Department;

public interface DepartmentService{
  public Department create(Department department);
  public Department update(Integer id, UpdateDepartmentRequest updateDepartmentRequest);
  public Boolean delete(Integer id);
}
