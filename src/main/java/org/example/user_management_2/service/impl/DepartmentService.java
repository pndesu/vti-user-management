package org.example.user_management_2.service.impl;

import org.example.user_management_2.dto.CreateDepartmentRequest;
import org.example.user_management_2.dto.UpdateDepartmentRequest;
import org.example.user_management_2.entity.Department;
import org.example.user_management_2.exception.BusinessException;
import org.example.user_management_2.repository.DepartmentRepository;
import org.example.user_management_2.service.IDepartmentService;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DepartmentService implements IDepartmentService {

  private final DepartmentRepository departmentRepository;

  @Override
  @Transactional
  public Department create(CreateDepartmentRequest department) {
    if (department.getName() == null) {
      throw new BusinessException("department name cannot be null");
    }

    Department existedDepartment = departmentRepository.findByName(department.getName());
    if (existedDepartment != null) {
      throw new BusinessException("department existed");
    }

    Department createdDepartment = new Department();
    createdDepartment.setName(department.getName());
    return departmentRepository.save(createdDepartment);
  }

  @Override
  @Transactional
  public Department update(Integer id, UpdateDepartmentRequest updateDepartmentRequest) {
    if (updateDepartmentRequest.getName() == null) {
      throw new BusinessException("Department name is not entered");
    }
    Department existedDepartment = departmentRepository.findById(id)
        .orElseThrow(() -> new BusinessException("Department not found"));
    if (departmentRepository.findByName(updateDepartmentRequest.getName()) != null){
      throw new BusinessException("Department name already existed");
    };
    existedDepartment.setName(updateDepartmentRequest.getName());
    return existedDepartment;
  }

  @Override
  @Transactional
  public Boolean delete(Integer id) {
    departmentRepository.findById(id).orElseThrow(() -> new BusinessException("Department not found"));
    departmentRepository.deleteById(id);
    return true;
  }
}
