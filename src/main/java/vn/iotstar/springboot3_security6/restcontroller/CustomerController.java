package vn.iotstar.springboot3_security6.restcontroller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@RestController
@EnableMethodSecurity
public class CustomerController {

    @GetMapping("/hello")
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("hello is Guest");
    }

    @GetMapping("/customer/all")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<String> getCustomerList() {
        return ResponseEntity.ok("Chào Admin! Truy cập thành công.");
    }

    @GetMapping("/customer/{id}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<String> getCustomer(@PathVariable("id") String id) {
        return ResponseEntity.ok("Chào User! Khách hàng ID: " + id);
    }
}