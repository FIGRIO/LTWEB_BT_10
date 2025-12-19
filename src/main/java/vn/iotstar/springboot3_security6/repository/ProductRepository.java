package vn.iotstar.springboot3_security6.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.iotstar.springboot3_security6.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}