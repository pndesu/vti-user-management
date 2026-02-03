package org.example.user_management_2.service.impl;

import org.example.user_management_2.dto.UpdateDepartmentRequest;
import org.example.user_management_2.entity.Department;
import org.example.user_management_2.repository.DepartmentRepository;
import org.example.user_management_2.service.DepartmentService;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

  private final DepartmentRepository departmentRepository;

  @Override
  @Transactional
  public Department create(Department department) {
    if (department.getName() == null){
      // TODO đã biết về business exception rồi thì toàn bộ phần này thay bằng business exception giúp anh
      throw new RuntimeException("department name cannot be null");
    }

    Department existedDepartment = departmentRepository.findByName(department.getName());
    if (existedDepartment != null){
      throw new RuntimeException("department existed");
    }

    // TODO nên return luôn: return departmentRepository.save(department); để code clean hơn
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
    // TODO ở api create đang thấy chêck trùng name, thì khi update cũng phải check trùng name,
    //  vì có thể họ sẽ update 1 cái name mà trùng với cái đã có trong db
    existedDepartment.setName(updateDepartmentRequest.getName());
    return existedDepartment;
  }

  @Override
  @Transactional
  public Boolean delete(Integer id) {
    // TODO không khai variable nếu không dùng, để clean và k cần sử dụng mem không cần thiết
    Department existedDepartment = departmentRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Department not found"));
    departmentRepository.deleteById(id);
    return true;
  }

}
