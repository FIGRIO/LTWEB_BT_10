package vn.iotstar.springboot3_security6.restcontroller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
public class CustomerController {

    // Đã xóa endpoint /hello để tránh xung đột với LoginController

    @GetMapping("/customer/all")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<String> getCustomerList() {
        return ResponseEntity.ok("Chào Admin! Đây là danh sách khách hàng.");
    }

    @GetMapping("/customer/{id}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<String> getCustomer(@PathVariable("id") String id) {
        return ResponseEntity.ok("Chào User! Bạn đang xem khách hàng: " + id);
    }
}