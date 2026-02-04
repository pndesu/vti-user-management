package org.example.user_management_2.service;

import java.util.*;

import org.example.user_management_2.dto.CreateUserRequest;
import org.example.user_management_2.dto.UpdateUserRequest;
import org.example.user_management_2.dto.UserFilter;
import org.example.user_management_2.entity.User;

public interface IUserService{
  List<User> getUsersFromService();
  User createUserFromService(CreateUserRequest user);
  User getUserByIdFromService(Integer id);
  List<User> getUsersByFirstName(String firstName);
  List<User> getUsersByLastName(String lastName);
  List<User> getUsersByName(String firstName, String lastName);
  User updateUser(Integer id, UpdateUserRequest updateUserRequest);
  Boolean deleteUser(Integer id);
  List<User> search(UserFilter userFilter);
}
