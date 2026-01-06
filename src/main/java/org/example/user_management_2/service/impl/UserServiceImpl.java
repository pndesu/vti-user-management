package org.example.user_management_2.service.impl;

import java.util.List;
import java.util.Optional;

import org.example.user_management_2.dto.UpdateUserRequest;
import org.example.user_management_2.entity.Department;
import org.example.user_management_2.entity.User;
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
        .orElseThrow(() -> new RuntimeException("User not found"));
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
  public User createUserFromService(User user) {
    if (user.getUsername() == null) {
      throw new RuntimeException("username must not be null");
    }
    if (user.getPassword() == null) {
      throw new RuntimeException("password must not be null");
    }
    if (user.getFirstName() == null) {
      throw new RuntimeException("first name must not be null");
    }
    if (user.getLastName() == null) {
      throw new RuntimeException("last name must not be null");
    }
    if (user.getRole() == null) {
      throw new RuntimeException("role must not be null");
    }
    if (user.getDepartment() == null) {
      throw new RuntimeException("department must not be null");
    }

    User existedUser = userRepository.findByUsername(user.getUsername());
    if (existedUser != null) {
      throw new RuntimeException("username already existed");
    }

    // Department existingDept =
    // departmentRepository.findById(user.getDepartment().getId())
    // .orElseThrow(() -> new RuntimeException("Department not found"));
    // user.setDepartment(existingDept);

    Optional<Department> department = departmentRepository.findById(user.getDepartment().getId());
    if (department.isEmpty()) { // department == null
      throw new RuntimeException("department not found");
    }
    user.setDepartment(department.get());

    User createdUser = userRepository.save(user);
    return createdUser;
  }

  @Override
  @Transactional
  public User updateUser(Integer id, UpdateUserRequest updateUserRequest) {
    if (updateUserRequest.getPassword() == null) {throw new RuntimeException("Password is not entered");}
    if (updateUserRequest.getFirstName() == null) {throw new RuntimeException("FirstName is not entered");}
    if (updateUserRequest.getLastName() == null) {throw new RuntimeException("LastName is not entered");}
    if (updateUserRequest.getDepartmentId() == null) {throw new RuntimeException("DepartmentId is not entered");}
    User existedUser = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    Department department = departmentRepository.findById(updateUserRequest.getDepartmentId()).orElseThrow(() -> new RuntimeException("Department not found"));
    existedUser.setPassword(updateUserRequest.getPassword());
    existedUser.setFirstName(updateUserRequest.getFirstName());
    existedUser.setLastName(updateUserRequest.getLastName());
    existedUser.setDepartment(department);
    return existedUser;
  }

  @Override
  @Transactional
  public Boolean deleteUser(Integer id) {
    User existedUser = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    userRepository.deleteById(id);
    return true;
  }

}
