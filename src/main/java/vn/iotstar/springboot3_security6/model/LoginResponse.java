package vn.iotstar.springboot3_security6.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {
    private String token; // Chuỗi JWT
    private long expiresIn; // Thời gian hết hạn (tính bằng mili giây)
    private String username; // Tên tài khoản
}