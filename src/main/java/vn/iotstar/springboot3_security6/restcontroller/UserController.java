package vn.iotstar.springboot3_security6.restcontroller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import vn.iotstar.springboot3_security6.entity.UserInfo;
import vn.iotstar.springboot3_security6.service.UserService;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/new")
    public String addUser(@RequestBody UserInfo userInfo) {
        // Gọi service để thêm user (hàm này trả về void)
        userService.addUser(userInfo);
        
        // Trả về thông báo thành công thủ công
        return "Thêm user thành công!";
    }
}