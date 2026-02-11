package org.example.user_management_2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UserManagement2Application {

    public static void main(String[] args) {
        SpringApplication.run(UserManagement2Application.class, args);
    }

    // todo trả lời các câu hỏi sau bằng record:
    // 1. Restfull api là gì,  khi nào dùng GET, PUT, DELETE, POST => trong TH nào searching có thẻe dùng POST?
    // 2. tại sao phải tạo class BaseResponse
    // 3. Tại sao phải bọc BaseResponse trong ResponseEntity
    // 4. specification dùng để làm gì? nếu không có thì sao? tại sao lại cần API có nhiều điều kiẹn filter
    // 5. dto dùng để làm gì? tại sao cần tạo nó
    // 6. global exception là gì? tại sao phải luôn bắt class: Exception
    // 7. Giữa method name, HQL, native query thì thứ tự ưu tiên sử dụng là gì? tại sao ?

}
