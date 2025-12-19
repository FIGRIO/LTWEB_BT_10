package vn.iotstar.springboot3_security6.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import vn.iotstar.springboot3_security6.entity.AuthRequest;
import vn.iotstar.springboot3_security6.entity.UserInfo;
import vn.iotstar.springboot3_security6.model.LoginResponse;
import vn.iotstar.springboot3_security6.repository.UserInfoRepository;
import vn.iotstar.springboot3_security6.service.JwtService;
import vn.iotstar.springboot3_security6.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserInfoRepository repository;

    // API Thêm User
    @PostMapping("/new")
    public String addUser(@RequestBody UserInfo userInfo) {
        userService.addUser(userInfo);
        return "User Added Successfully";
    }

    // API Đăng nhập lấy Token
    @PostMapping("/authenticate")
    public ResponseEntity<LoginResponse> authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
        );

        if (authentication.isAuthenticated()) {
            String token = jwtService.generateToken(authRequest.getUsername());
            // Token hết hạn sau 30 phút (1800000 ms)
            LoginResponse response = new LoginResponse(token, 1800000, authRequest.getUsername());
            return ResponseEntity.ok(response);
        } else {
            throw new UsernameNotFoundException("Sai thông tin đăng nhập!");
        }
    }

    // API Lấy thông tin người dùng hiện tại (Yêu cầu Token)
    @GetMapping("/me")
    public ResponseEntity<UserInfo> getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            String username = ((UserDetails) authentication.getPrincipal()).getUsername();
            return repository.findByName(username)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        }
        return ResponseEntity.status(401).build();
    }
}