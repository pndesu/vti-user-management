package org.example.user_management_2.specification;

import java.util.List;

import org.example.user_management_2.entity.User;
import org.springframework.data.jpa.domain.Specification;

import jakarta.persistence.criteria.Predicate;

public class UserSpecification {
  public static Specification<User> firstNameLike(String firstName) {
    return ((root, query, builder) -> {
      if (firstName == null) {
        return builder.conjunction();
      }
      return builder.like(builder.lower(root.get("firstName")), "%" + firstName.toLowerCase() + "%");
    });
  }

  public static Specification<User> lastNameLike(String lastName) {
    return ((root, query, builder) -> {
      if (lastName == null) {
        return builder.conjunction();
      }
      return builder.like(builder.lower(root.get("lastName")), "%" + lastName.toLowerCase() + "%");
    });
  }

  public static Specification<User> idIn(List<Integer> ids) {
    return ((root, query, builder) -> {
      if (ids == null || ids.isEmpty()) {
        return builder.conjunction();
      }
      return root.get("id").in(ids);
    });
  }

  public static Specification<User> departmentIdIn(List<Integer> departmentIds) {
    return ((root, query, builder) -> {
      if (departmentIds == null || departmentIds.isEmpty()) {
        return builder.conjunction();
      }
      return root.get("department").get("id").in(departmentIds);
    });
  }

  public static Specification<User> departmentIdEquals(Integer departmentId) {
    return ((root, query, builder) -> {
      if (departmentId == null) {
        return builder.conjunction();
      }

      return builder.equal(root.get("department").get("id"), departmentId);
    });
  }

  public static Specification<User> roleEquals(String role) {
    return ((root, query, builder) -> {
      if (role == null) {
        return builder.conjunction();
      }
      return builder.equal(builder.lower(root.get("role")), role.toLowerCase());
    });
  }

  public static Specification<User> nameLike(String name) {
    return ((root, query, builder) -> {
      if (name == null || name.isBlank()) {
        return builder.conjunction();
      }
      String pattern = "%" + name + "%";
      Predicate usernameLike = builder.like(builder.lower(root.get("username")), pattern);
      Predicate firstNameLike = builder.like(builder.lower(root.get("firstName")), pattern);
      Predicate lastNameLike = builder.like(builder.lower(root.get("lastName")), pattern);

      return builder.or(usernameLike, firstNameLike, lastNameLike);
    });
  }
}
