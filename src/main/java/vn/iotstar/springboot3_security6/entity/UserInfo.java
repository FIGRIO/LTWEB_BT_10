package vn.iotstar.springboot3_security6.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data // Tự động tạo getter, setter 
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) [cite: 1569]
    private int id;
    private String name;
    private String email;
    private String password;
    private String roles;
}