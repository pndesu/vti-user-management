package org.example.user_management_2.controller;

import java.util.List;

import org.example.user_management_2.common.BaseResponse;
import org.example.user_management_2.dto.CreateDepartmentRequest;
import org.example.user_management_2.dto.DepartmentFilter;
import org.example.user_management_2.dto.UpdateDepartmentRequest;
import org.example.user_management_2.entity.Department;
import org.example.user_management_2.service.IDepartmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/departments")
public class DepartmentController {
  private final IDepartmentService departmentService;

  @PostMapping("/search")
  public ResponseEntity<BaseResponse<List<Department>>> search(DepartmentFilter departmentFilter) {
    List<Department> data = departmentService.search(departmentFilter);
    BaseResponse<List<Department>> response = new BaseResponse<List<Department>>(data,
        "Retrieved department information successfully");
    return ResponseEntity.ok(response);
  }

  @PostMapping
  public ResponseEntity<BaseResponse<Department>> createDepartment(@RequestBody CreateDepartmentRequest department) {
    Department createdDepartment = departmentService.create(department);
    BaseResponse<Department> response = new BaseResponse<Department>(createdDepartment,
        "Created department successfully");
    return ResponseEntity.ok(response);
  }

  @PutMapping("/{id}")
  public ResponseEntity<BaseResponse<Department>> updateDepartment(@PathVariable Integer id,
      @RequestBody UpdateDepartmentRequest updateDepartmentRequest) {
    Department data = departmentService.update(id, updateDepartmentRequest);
    BaseResponse<Department> response = new BaseResponse<Department>(data, "updated department successfully");
    return ResponseEntity.ok(response);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<BaseResponse<Boolean>> deleteDepartment(@PathVariable Integer id) {
    Boolean data = departmentService.delete(id);
    BaseResponse<Boolean> response = new BaseResponse<Boolean>(data, "deleted department successfully");
    return ResponseEntity.ok(response);
  }
}
