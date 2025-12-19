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

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @GetMapping("/hello")
    public String home() {
        return "home";
    }
    
    @GetMapping("/403")
    public String accessDenied() {
        return "403";
    }

    @GetMapping("/signup")
    public String signup(Model model) {
        model.addAttribute("userInfo", new UserInfo());
        return "signup";
    }

    @PostMapping("/signup")
    public String processSignup(@ModelAttribute UserInfo userInfo) {
        userService.addUser(userInfo);
        return "redirect:/login?success";
    }

    @GetMapping("/login-jwt")
    public String showJwtLoginPage() {
        return "login_jwt";
    }

    @GetMapping("/profile")
    public String showProfilePage() {
        return "profile";
    }

    // --- MỚI: Trang Quản lý sản phẩm ---
    @GetMapping("/products-view")
    public String showProductPage() {
        return "products"; // Trả về file products.html
    }
}