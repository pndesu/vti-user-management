package org.example.user_management_2.service;

import org.example.user_management_2.dto.UpdateDepartmentRequest;
import org.example.user_management_2.entity.Department;

public interface DepartmentService{
  // TODO có thể bỏ public, default không nói gì thì access-modifier của METHOD trong interface là public
  public Department create(Department department);
  public Department update(Integer id, UpdateDepartmentRequest updateDepartmentRequest);
  public Boolean delete(Integer id);
}
