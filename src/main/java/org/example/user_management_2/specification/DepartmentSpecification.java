package org.example.user_management_2.specification;

import java.time.Instant;
import java.util.List;

import org.example.user_management_2.entity.Department;
import org.springframework.data.jpa.domain.Specification;

public class DepartmentSpecification{
  public static Specification<Department> idIn(List<Integer> ids){
    return ((root, query, builder) -> {
      if (ids == null || ids.isEmpty()){
        return builder.conjunction();
      }
      return root.get("id").in(ids);
    });
  }

  public static Specification<Department> nameLike(String name){
    return ((root, query, builder) -> {
      if (name == null){
        return builder.conjunction();
      }
      return builder.like(builder.lower(root.get("name")), "%" + name.toLowerCase() + "%");
    });
  }

  public static Specification<Department> createdDateFrom(Instant date){
    return ((root, query, builder) -> {
      if (date == null){
        return builder.conjunction();
      }
      return builder.greaterThanOrEqualTo(root.get("createdDate"), date);
    });
  }
  public static Specification<Department> createdDateTo(Instant date){
    return ((root, query, builder) -> {
      if (date == null){
        return builder.conjunction();
      }
      return builder.lessThanOrEqualTo(root.get("createdDate"), date);
    });
  }
}
