package org.example.user_management_2.repository;

import java.util.List;

import org.example.user_management_2.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserRepository extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User>{
  User findByUsername(String username);
  List<User> findByFirstNameContainingIgnoreCase(String firstName);
  List<User> findByLastNameContainingIgnoreCase(String lastName);
  List<User> findByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCase(String firstName, String lastName);
}
