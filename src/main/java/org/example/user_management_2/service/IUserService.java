package org.example.user_management_2.service;

import java.util.*;

import org.example.user_management_2.dto.CreateUserRequest;
import org.example.user_management_2.dto.UpdateUserRequest;
import org.example.user_management_2.dto.UserFilter;
import org.example.user_management_2.dto.UserFilterV2;
import org.example.user_management_2.entity.User;

public interface IUserService{
  List<User> get();
  User create(CreateUserRequest user);
  User getById(Integer id);
  List<User> getByFirstName(String firstName);
  List<User> getByLastName(String lastName);
  List<User> getByName(String firstName, String lastName);
  User update(Integer id, UpdateUserRequest updateUserRequest);
  Boolean delete(Integer id);
  List<User> search(UserFilter userFilter);
  List<User> searchv2(UserFilterV2 userFilter);
}
