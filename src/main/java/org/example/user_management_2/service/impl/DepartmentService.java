package org.example.user_management_2.service.impl;

import java.util.List;

import org.example.user_management_2.dto.CreateDepartmentRequest;
import org.example.user_management_2.dto.DepartmentFilter;
import org.example.user_management_2.dto.UpdateDepartmentRequest;
import org.example.user_management_2.entity.Department;
import org.example.user_management_2.exception.BusinessException;
import org.example.user_management_2.repository.DepartmentRepository;
import org.example.user_management_2.service.IDepartmentService;
import org.example.user_management_2.specification.DepartmentSpecification;
import org.springframework.data.jpa.domain.Specification;
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
    if (departmentRepository.findByName(updateDepartmentRequest.getName()) != null) {
      throw new BusinessException("Department name already existed");
    }
    ;
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

  @Override
  public List<Department> search(DepartmentFilter departmentFilter) {
    Specification<Department> spec = Specification.where(null);

    if (departmentFilter.getIds() != null) {
      spec = spec.and(DepartmentSpecification.idIn(departmentFilter.getIds()));
    }
    if (departmentFilter.getName() != null) {
      spec = spec.and(DepartmentSpecification.nameLike(departmentFilter.getName()));
    }
    if (departmentFilter.getCreatedDateFrom() != null) {
      spec = spec.and(DepartmentSpecification.createdDateFrom(departmentFilter.getCreatedDateFrom()));
    }
    if (departmentFilter.getCreatedDateTo() != null) {
      spec = spec.and(DepartmentSpecification.createdDateTo(departmentFilter.getCreatedDateTo()));
    }
    return departmentRepository.findAll(spec);
  }
}
