package vn.iotstar.springboot3_security6.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import vn.iotstar.springboot3_security6.entity.Product;
import vn.iotstar.springboot3_security6.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService service;

    // Xem danh sách: Ai cũng xem được (hoặc yêu cầu ROLE_USER tùy bạn)
    @GetMapping("/all")
    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_ADMIN')")
    public List<Product> list() {
        return service.listAll();
    }

    // Xem chi tiết
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_ADMIN')")
    public ResponseEntity<Product> get(@PathVariable Long id) {
        Product product = service.get(id);
        if (product == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(product);
    }

    // Thêm mới: Chỉ ADMIN
    @PostMapping("/new")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Product> add(@RequestBody Product product) {
        service.save(product);
        return ResponseEntity.ok(product);
    }

    // Sửa: Chỉ ADMIN
    @PutMapping("/edit/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<?> update(@RequestBody Product product, @PathVariable Long id) {
        Product existProduct = service.get(id);
        if (existProduct == null) return ResponseEntity.notFound().build();
        
        product.setId(id); // Đảm bảo ID đúng
        service.save(product);
        return ResponseEntity.ok("Cập nhật thành công!");
    }

    // Xóa: Chỉ ADMIN
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok("Xóa thành công!");
    }
}