package vn.iotstar.springboot3_security6.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import vn.iotstar.springboot3_security6.entity.UserInfo;
import vn.iotstar.springboot3_security6.repository.UserInfoRepository;

@Service
public class UserService {

    @Autowired
    private UserInfoRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Phương thức thêm user mới vào Database
    public void addUser(UserInfo userInfo) {
        // Mã hóa mật khẩu trước khi lưu
        userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
        
        // Nếu user chưa có Role, mặc định set là ROLE_USER
        if (userInfo.getRoles() == null || userInfo.getRoles().isEmpty()) {
            userInfo.setRoles("ROLE_USER");
        }
        
        repository.save(userInfo);
    }
}