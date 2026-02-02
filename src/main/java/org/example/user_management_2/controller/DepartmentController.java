package org.example.user_management_2.controller;

import org.example.user_management_2.common.BaseResponse;
import org.example.user_management_2.dto.UpdateDepartmentRequest;
import org.example.user_management_2.entity.Department;
import org.example.user_management_2.service.DepartmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class DepartmentController {
  private final DepartmentService departmentService;
  
  @PostMapping("/v1/departments")
  public ResponseEntity<BaseResponse<Department>> createDepartment(@RequestBody Department department){
    Department createdDepartment = departmentService.create(department);
    BaseResponse<Department> response = new BaseResponse<Department>(department, "Created department successfully");
    return ResponseEntity.ok(response);
  }

  @PutMapping("/v1/departments/{id}")
  public ResponseEntity<BaseResponse<Department>> updateDepartment(@PathVariable Integer id, @RequestBody UpdateDepartmentRequest updateDepartmentRequest){
    Department data = departmentService.update(id, updateDepartmentRequest);
    BaseResponse<Department> response = new BaseResponse<Department>(data, "updated department successfully");
    return ResponseEntity.ok(response);
  }

  @DeleteMapping("/v1/departments/{id}")
  public ResponseEntity<BaseResponse<Boolean>> deleteDepartment(@PathVariable Integer id){
    Boolean data = departmentService.delete(id);
    BaseResponse<Boolean> response = new BaseResponse<Boolean>(data, "deleted department successfully");
    return ResponseEntity.ok(response);
  }
}
