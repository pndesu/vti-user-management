package org.example.user_management_2.repository;

import org.example.user_management_2.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface DepartmentRepository extends JpaRepository<Department, Integer>, JpaSpecificationExecutor<Department>{
  Department findByName(String name);
}
