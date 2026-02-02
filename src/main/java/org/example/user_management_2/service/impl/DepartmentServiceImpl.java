package org.example.user_management_2.service.impl;

import org.example.user_management_2.dto.UpdateDepartmentRequest;
import org.example.user_management_2.entity.Department;
import org.example.user_management_2.repository.DepartmentRepository;
import org.example.user_management_2.service.DepartmentService;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class DepartmentServiceImpl implements DepartmentService {

  private DepartmentRepository departmentRepository;

  public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
    this.departmentRepository = departmentRepository;
  }

  @Override
  @Transactional
  public Department create(Department department) {
    if (department.getName() == null){
      throw new RuntimeException("department name cannot be null");
    }

    Department existedDepartment = departmentRepository.findByName(department.getName());
    if (existedDepartment != null){
      throw new RuntimeException("department existed");
    }

    Department createdDepartment = departmentRepository.save(department);
    return createdDepartment;
  }

  @Override
  @Transactional
  public Department update(Integer id, UpdateDepartmentRequest updateDepartmentRequest) {
    if (updateDepartmentRequest.getName() == null) {
      throw new RuntimeException("Department name is not entered");
    }
    Department existedDepartment = departmentRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Department not found"));
    existedDepartment.setName(updateDepartmentRequest.getName());
    return existedDepartment;
  }

  @Override
  @Transactional
  public Boolean delete(Integer id) {
    Department existedDepartment = departmentRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Department not found"));
    departmentRepository.deleteById(id);
    return true;
  }

}
