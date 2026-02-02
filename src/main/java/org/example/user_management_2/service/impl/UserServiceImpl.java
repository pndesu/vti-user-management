package org.example.user_management_2.service.impl;

import java.util.List;
import java.util.Optional;

import org.example.user_management_2.dto.CreateUserRequest;
import org.example.user_management_2.dto.UpdateUserRequest;
import org.example.user_management_2.entity.Department;
import org.example.user_management_2.entity.User;
import org.example.user_management_2.exception.BusinessException;
import org.example.user_management_2.repository.DepartmentRepository;
import org.example.user_management_2.repository.UserRepository;
import org.example.user_management_2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class UserServiceImpl implements UserService {

  private UserRepository userRepository;
  private DepartmentRepository departmentRepository;

  public UserServiceImpl(UserRepository userRepository, DepartmentRepository departmentRepository) {
    this.userRepository = userRepository;
    this.departmentRepository = departmentRepository;
  }

  @Override
  public List<User> getUsersFromService() {
    return userRepository.findAll();
  }

  @Override
  public User getUserByIdFromService(Integer id) {
    User existedUser = userRepository.findById(id)
        .orElseThrow(() -> new BusinessException("User not found"));
    return existedUser;
  }

  @Override
  public List<User> getUsersByFirstName(String firstName) {
    List<User> userList = userRepository.findByFirstNameContainingIgnoreCase(firstName);
    return userList;
  }

  @Override
  public List<User> getUsersByLastName(String lastName) {
    List<User> userList = userRepository.findByLastNameContainingIgnoreCase(lastName);
    return userList;
  }

  @Override
  public List<User> getUsersByName(String firstName, String lastName) {
    if (firstName == null && lastName == null) {
      return userRepository.findAll();
    } else if (firstName != null && lastName == null) {
      return userRepository.findByFirstNameContainingIgnoreCase(firstName);
    } else if (firstName == null && lastName != null) {
      return userRepository.findByLastNameContainingIgnoreCase(lastName);
    } else {
      return userRepository.findByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCase(firstName, lastName);
    }
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
    //   throw new BusinessException("role must not be null");
    // }
    // if (user.getDepartment() == null) {
    //   throw new BusinessException("department must not be null");
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
    if (user.getDepartment() != null){
      Optional<Department> department = departmentRepository.findById(user.getDepartment().getId());
      if (department.isEmpty()) { // department == null
        throw new BusinessException("department not found");
      }
      createdUser.setDepartment(user.getDepartment());
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
    if (updateUserRequest.getPassword() == null) {throw new BusinessException("Password is not entered");}
    if (updateUserRequest.getFirstName() == null) {throw new BusinessException("FirstName is not entered");}
    if (updateUserRequest.getLastName() == null) {throw new BusinessException("LastName is not entered");}
    if (updateUserRequest.getDepartmentId() == null) {throw new BusinessException("DepartmentId is not entered");}
    User existedUser = userRepository.findById(id).orElseThrow(() -> new BusinessException("User not found"));
    Department department = departmentRepository.findById(updateUserRequest.getDepartmentId()).orElseThrow(() -> new BusinessException("Department not found"));
    existedUser.setPassword(updateUserRequest.getPassword());
    existedUser.setFirstName(updateUserRequest.getFirstName());
    existedUser.setLastName(updateUserRequest.getLastName());
    existedUser.setDepartment(department);
    return existedUser;
  }

  @Override
  @Transactional
  public Boolean deleteUser(Integer id) {
    User existedUser = userRepository.findById(id).orElseThrow(() -> new BusinessException("User not found"));
    userRepository.deleteById(id);
    return true;
  }

}
