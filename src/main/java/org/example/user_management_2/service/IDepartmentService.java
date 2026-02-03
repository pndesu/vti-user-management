package org.example.user_management_2.service;

import org.example.user_management_2.dto.CreateDepartmentRequest;
import org.example.user_management_2.dto.UpdateDepartmentRequest;
import org.example.user_management_2.entity.Department;

public interface IDepartmentService{
  Department create(CreateDepartmentRequest department);
  Department update(Integer id, UpdateDepartmentRequest updateDepartmentRequest);
  Boolean delete(Integer id);
}
