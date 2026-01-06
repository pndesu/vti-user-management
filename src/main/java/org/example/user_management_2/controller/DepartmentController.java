package org.example.user_management_2.controller;

import org.example.user_management_2.dto.UpdateDepartmentRequest;
import org.example.user_management_2.entity.Department;
import org.example.user_management_2.service.DepartmentService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DepartmentController {
  private DepartmentService departmentService;

  public DepartmentController(DepartmentService departmentService){
    this.departmentService = departmentService;
  }

  @PutMapping("/v1/departments/{id}")
  public Department updateDepartment(@PathVariable Integer id, @RequestBody UpdateDepartmentRequest updateDepartmentRequest){
    return departmentService.update(id, updateDepartmentRequest);
  }

  @DeleteMapping("/v1/departments/{id}")
  public Boolean deleteDepartment(@PathVariable Integer id){
    return departmentService.delete(id);
  }
}
