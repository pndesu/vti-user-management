package org.example.user_management_2.specification;

import java.util.List;

import org.example.user_management_2.entity.User;
import org.springframework.data.jpa.domain.Specification;

public class UserSpecification{
  public static Specification<User> firstNameLike(String firstName){
    return ((root, query, builder) -> {
      if (firstName == null){
        return builder.conjunction();
      }
      return builder.like(builder.lower(root.get("firstName")), "%" + firstName.toLowerCase() + "%");
    });
  }

  public static Specification<User> lastNameLike(String lastName){
    return ((root, query, builder) -> {
      if (lastName == null){
        return builder.conjunction();
      }
      return builder.like(builder.lower(root.get("lastName")), "%" + lastName.toLowerCase() + "%");
    });
  }

  public static Specification<User> idIn(List<Integer> ids){
    return((root, query, builder) -> {
      if (ids == null || ids.isEmpty()){
        return builder.conjunction();
      }
      return root.get("id").in(ids);
    });
  }

  public static Specification<User> departmentIdIn(List<Integer> departmentIds){
    return((root, query, builder) -> {
      if (departmentIds == null || departmentIds.isEmpty()){
        return builder.conjunction();
      }
      return root.get("department").get("id").in(departmentIds);
    });
  }
  
  public static Specification<User> roleEquals(String role){
    return ((root, query, builder) -> {
      if (role == null){
        return builder.conjunction();
      }
      return builder.equal(builder.lower(root.get("role")), role.toLowerCase());
    });
  }
}
