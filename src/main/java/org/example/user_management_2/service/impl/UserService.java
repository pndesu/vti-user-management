package org.example.user_management_2.service.impl;

import java.util.List;
import java.util.Optional;

import org.example.user_management_2.dto.CreateUserRequest;
import org.example.user_management_2.dto.UpdateUserRequest;
import org.example.user_management_2.dto.UserFilter;
import org.example.user_management_2.entity.Department;
import org.example.user_management_2.entity.User;
import org.example.user_management_2.exception.BusinessException;
import org.example.user_management_2.repository.DepartmentRepository;
import org.example.user_management_2.repository.UserRepository;
import org.example.user_management_2.service.IUserService;
import org.example.user_management_2.specification.UserSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.rsocket.RSocketProperties.Server.Spec;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.util.Separators;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

  private final UserRepository userRepository;
  private final DepartmentRepository departmentRepository;

  @Override
  public List<User> getUsersFromService() {
    return userRepository.findAll();
  }

  @Override
  public User getUserByIdFromService(Integer id) {
    return userRepository.findById(id)
        .orElseThrow(() -> new BusinessException("User not found"));
  }

  @Override
  public List<User> getUsersByFirstName(String firstName) {
    return userRepository.findByFirstNameContainingIgnoreCase(firstName);
  }

  @Override
  public List<User> getUsersByLastName(String lastName) {
    return userRepository.findByLastNameContainingIgnoreCase(lastName);
  }

  @Override
  public List<User> getUsersByName(String firstName, String lastName) {
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
  public User createUserFromService(CreateUserRequest user) {
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
    return createdUser;
  }

  @Override
  @Transactional
  public User updateUser(Integer id, UpdateUserRequest updateUserRequest) {
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
    return existedUser;
  }

  @Override
  @Transactional
  public Boolean deleteUser(Integer id) {
    userRepository.findById(id).orElseThrow(() -> new BusinessException("User not found"));
    userRepository.deleteById(id);
    return true;
  }

  @Override
  public List<User> search(UserFilter userFilter) {
    System.out.println("FirstName received: " + userFilter.getFirstName());
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

}
