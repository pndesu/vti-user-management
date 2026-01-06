package org.example.user_management_2.controller;

import java.util.List;

import org.example.user_management_2.dto.UpdateUserRequest;
import org.example.user_management_2.entity.User;
import org.example.user_management_2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
  @Autowired
  private UserService userService;

  @GetMapping("/v1/users")
  public List<User> getUsers() {
    return userService.getUsersFromService();
  }

  @GetMapping("/v1/users/{id}")
  public User getUser(@PathVariable Integer id) {
    return userService.getUserByIdFromService(id);
  }

  @GetMapping("/v1/users/first-name")
  public List<User> findByFirstName(@RequestParam String firstName) {
    return userService.getUsersByFirstName(firstName);
  }

  @GetMapping("/v1/users/last-name")
  public List<User> findByLastName(@RequestParam String lastName) {
    return userService.getUsersByLastName(lastName);
  }

  @GetMapping("/v1/users/first-name-and-last-name")
  public List<User> findByName(@RequestParam (required = false) String firstName, @RequestParam (required = false) String lastName) {
    return userService.getUsersByName(firstName, lastName);
  }

  @PostMapping("/v1/users")
  public User createUser(@RequestBody User user) {
    return userService.createUserFromService(user);
  }

  @PutMapping("/v1/users/{id}")
  public User updateUser(@PathVariable Integer id, @RequestBody UpdateUserRequest updateUserRequest){
    return userService.updateUser(id, updateUserRequest);
  }

  @DeleteMapping("/v1/users/{id}")
  public Boolean deleteUser(@PathVariable Integer id){
    return userService.deleteUser(id);
  }
}
