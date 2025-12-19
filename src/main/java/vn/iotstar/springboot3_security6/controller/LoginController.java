package vn.iotstar.springboot3_security6.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import vn.iotstar.springboot3_security6.entity.UserInfo;
import vn.iotstar.springboot3_security6.service.UserService;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    // Trang Login
    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    // Trang Home (sau khi đăng nhập thành công)
    @GetMapping("/hello")
    public String home() {
        return "home";
    }
    
    // Trang 403 (khi không đủ quyền)
    @GetMapping("/403")
    public String accessDenied() {
        return "403";
    }

    // --- PHẦN ĐĂNG KÝ ---

    // Hiển thị form đăng ký (GET)
    @GetMapping("/signup")
    public String signup(Model model) {
        model.addAttribute("userInfo", new UserInfo()); // Tạo object rỗng để form binding
        return "signup";
    }

    // Xử lý submit form đăng ký (POST)
    @PostMapping("/signup")
    public String processSignup(@ModelAttribute UserInfo userInfo) {
        userService.addUser(userInfo); // Lưu vào DB
        return "redirect:/login?success"; // Chuyển hướng về login kèm thông báo thành công
    }
}