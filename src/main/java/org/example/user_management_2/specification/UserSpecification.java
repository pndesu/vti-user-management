package org.example.user_management_2.specification;

import org.example.user_management_2.entity.User;
import org.springframework.data.jpa.domain.Specification;

public class UserSpecification{
  public static Specification<User> firstNameLike(String firstName){
    return ((root, query, builder) -> {
      return builder.like(builder.lower(root.get("firstName")), "%s" + firstName.toLowerCase() + "%s");
    });
  }

  public static Specification<User> lastNameLike(String lastName){
    return ((root, query, builder) -> {
      return builder.like(builder.lower(root.get("lastName")), "%s" + lastName.toLowerCase() + "%s");
    });
  }
}
