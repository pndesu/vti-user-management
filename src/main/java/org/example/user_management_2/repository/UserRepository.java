package org.example.user_management_2.repository;

import java.util.List;

import org.example.user_management_2.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer>{
  User findByUsername(String username);
  List<User> findByFirstNameContainingIgnoreCase(String firstName);
  List<User> findByLastNameContainingIgnoreCase(String lastName);
  List<User> findByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCase(String firstName, String lastName);
}
