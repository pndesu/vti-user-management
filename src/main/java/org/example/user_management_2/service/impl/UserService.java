package org.example.user_management_2.service.impl;

import java.util.List;
import java.util.Optional;

import org.example.user_management_2.dto.*;
import org.example.user_management_2.entity.*;
import org.example.user_management_2.exception.BusinessException;
import org.example.user_management_2.repository.*;
import org.example.user_management_2.service.IUserService;
import org.example.user_management_2.specification.UserSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

  private final UserRepository userRepository;
  private final DepartmentRepository departmentRepository;

  @Override
  public List<User> get() {
    return userRepository.findAll();
  }

  @Override
  public User getById(Integer id) {
    return userRepository.findById(id)
        .orElseThrow(() -> new BusinessException("User not found"));
  }

  @Override
  public List<User> getByFirstName(String firstName) {
    return userRepository.findByFirstNameContainingIgnoreCase(firstName);
  }

  @Override
  public List<User> getByLastName(String lastName) {
    return userRepository.findByLastNameContainingIgnoreCase(lastName);
  }

  @Override
  public List<User> getByName(String firstName, String lastName) {
    Specification<User> spec = Specification.where(null);

    if (firstName != null) {
      spec = spec.and(UserSpecification.firstNameLike(firstName));
    }

    if (lastName != null) {
      spec = spec.and(UserSpecification.lastNameLike(lastName));
    }

    return userRepository.findAll(spec);
    // if (firstName == null && lastName == null) {
    // return userRepository.findAll();
    // } else if (firstName != null && lastName == null) {
    // return userRepository.findByFirstNameContainingIgnoreCase(firstName);
    // } else if (firstName == null) {
    // return userRepository.findByLastNameContainingIgnoreCase(lastName);
    // } else {
    // return
    // userRepository.findByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCase(firstName,
    // lastName);
    // }
  }

  @Override
  @Transactional
  public User create(CreateUserRequest user) {
    if (user.getUsername() == null) {
      throw new BusinessException("username must not be null");
    }
    if (user.getPassword() == null) {
      throw new BusinessException("password must not be null");
    }
    if (user.getFirstName() == null) {
      throw new BusinessException("first name must not be null");
    }
    if (user.getLastName() == null) {
      throw new BusinessException("last name must not be null");
    }
    // if (user.getRole() == null) {
    // throw new BusinessException("role must not be null");
    // }
    // if (user.getDepartment() == null) {
    // throw new BusinessException("department must not be null");
    // }

    User existedUser = userRepository.findByUsername(user.getUsername());
    if (existedUser != null) {
      throw new BusinessException("username already existed");
    }

    // Department existingDept =
    // departmentRepository.findById(user.getDepartment().getId())
    // .orElseThrow(() -> new BusinessException("Department not found"));
    // user.setDepartment(existingDept);
    User createdUser = new User();
    if (user.getDepartmentId() != null) {
      Optional<Department> department = departmentRepository.findById(user.getDepartmentId());
      if (department.isEmpty()) { // department == null
        throw new BusinessException("department not found");
      }
      createdUser.setDepartment(department.get());
    }
    createdUser.setUsername(user.getUsername());
    createdUser.setPassword(user.getPassword());
    createdUser.setFirstName(user.getFirstName());
    createdUser.setLastName(user.getLastName());
    return userRepository.save(createdUser);
  }

  @Override
  @Transactional
  public User update(Integer id, UpdateUserRequest updateUserRequest) {
    if (updateUserRequest.getPassword() == null) {
      throw new BusinessException("Password is not entered");
    }
    if (updateUserRequest.getFirstName() == null) {
      throw new BusinessException("FirstName is not entered");
    }
    if (updateUserRequest.getLastName() == null) {
      throw new BusinessException("LastName is not entered");
    }
    if (updateUserRequest.getDepartmentId() == null) {
      throw new BusinessException("DepartmentId is not entered");
    }
    User existedUser = userRepository.findById(id).orElseThrow(() -> new BusinessException("User not found"));
    Department department = departmentRepository.findById(updateUserRequest.getDepartmentId())
        .orElseThrow(() -> new BusinessException("Department not found"));
    existedUser.setPassword(updateUserRequest.getPassword());
    existedUser.setFirstName(updateUserRequest.getFirstName());
    existedUser.setLastName(updateUserRequest.getLastName());
    existedUser.setDepartment(department);
    return userRepository.save(existedUser);
  }

  @Override
  @Transactional
  public Boolean delete(Integer id) {
    userRepository.findById(id).orElseThrow(() -> new BusinessException("User not found"));
    userRepository.deleteById(id);
    return true;
  }

  @Override
  public List<User> search(UserFilter userFilter) {
    log.info("User filter received: {}", userFilter);
    Specification<User> spec = Specification.where(null);

    if (userFilter.getIds() != null) {
      spec = spec.and(UserSpecification.idIn(userFilter.getIds()));
    }

    if (userFilter.getDepartmentIds() != null) {
      spec = spec.and(UserSpecification.departmentIdIn(userFilter.getDepartmentIds()));
    }

    if (userFilter.getFirstName() != null) {
      spec = spec.and(UserSpecification.firstNameLike(userFilter.getFirstName()));
    }

    if (userFilter.getLastName() != null) {
      spec = spec.and(UserSpecification.lastNameLike(userFilter.getLastName()));
    }

    if (userFilter.getRole() != null){
      spec = spec.and(UserSpecification.roleEquals(userFilter.getRole()));
    }

    return userRepository.findAll(spec);
  }

  @Override
  public List<User> searchv2(UserFilterV2 userFilter) {
    Specification<User> spec = Specification.where(null);

    if (userFilter.getDepartmentId() != null){
      spec = spec.and(UserSpecification.departmentIdEquals(userFilter.getDepartmentId()));
    }

    if (userFilter.getRole() != null){
      spec = spec.and(UserSpecification.roleEquals(userFilter.getRole()));
    }

    if (userFilter.getName() != null){
      spec = spec.and(UserSpecification.nameLike(userFilter.getName()));
    }

    return userRepository.findAll(spec);
  }

}
