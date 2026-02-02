package org.example.user_management_2.controller;

import java.util.List;

import org.example.user_management_2.dto.UpdateUserRequest;
import org.example.user_management_2.entity.User;
import org.example.user_management_2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.example.user_management_2.common.*;

@RestController
public class UserController {
  @Autowired
  private UserService userService;

  @GetMapping("/v1/users")
  public ResponseEntity<BaseResponse<List<User>>> getUsers() {
    List<User> data = userService.getUsersFromService();
    BaseResponse<List<User>> response = new BaseResponse<List<User>>(data, "Retrieved user information successfully");
    return ResponseEntity.ok(response);
  }

  @GetMapping("/v1/users/{id}")
  public ResponseEntity<BaseResponse<User>> getUser(@PathVariable Integer id) {
    User data = userService.getUserByIdFromService(id); 
    BaseResponse<User> response = new BaseResponse<User>(data, "Retrieved user information successfully");
    return ResponseEntity.ok(response);
  }

  @GetMapping("/v1/users/first-name")
  public ResponseEntity<BaseResponse<List<User>>> findByFirstName(@RequestParam String firstName) {
    List<User> data = userService.getUsersByFirstName(firstName);
    BaseResponse<List<User>> response = new BaseResponse<List<User>>(data, "Retrieved user information successfully");
    return ResponseEntity.ok(response);
  }

  @GetMapping("/v1/users/last-name")
  public ResponseEntity<BaseResponse<List<User>>> findByLastName(@RequestParam String lastName) {
    List<User> data = userService.getUsersByLastName(lastName);
    BaseResponse<List<User>> response = new BaseResponse<List<User>>(data, "Retrieved user information successfully");
    return ResponseEntity.ok(response);
  }

  @GetMapping("/v1/users/first-name-and-last-name")
  public ResponseEntity<BaseResponse<List<User>>> findByName(@RequestParam (required = false) String firstName, @RequestParam (required = false) String lastName) {
    List<User> data = userService.getUsersByName(firstName, lastName);
    BaseResponse<List<User>> response = new BaseResponse<List<User>>(data, "Retrieved user information successfully");
    return ResponseEntity.ok(response);
  }

  @PostMapping("/v1/users")
  public ResponseEntity<BaseResponse<User>> createUser(@RequestBody User user) {
    User createdUser = userService.createUserFromService(user);
    BaseResponse<User> response = new BaseResponse<User>(user, "Created user successfully");
    return ResponseEntity.ok(response);
  }

  @PutMapping("/v1/users/{id}")
  public ResponseEntity<BaseResponse<User>> updateUser(@PathVariable Integer id, @RequestBody UpdateUserRequest updateUserRequest){
    User updatedUser = userService.updateUser(id, updateUserRequest);
    BaseResponse<User> response = new BaseResponse<User>(updatedUser, "Updated user successfully");
    return ResponseEntity.ok(response);
  }

  @DeleteMapping("/v1/users/{id}")
  public ResponseEntity<BaseResponse<Boolean>> deleteUser(@PathVariable Integer id){
    Boolean data = userService.deleteUser(id);
    BaseResponse<Boolean> response = new BaseResponse<Boolean>(data, "Deleted user successfully");
    return ResponseEntity.ok(response);
  }
}
