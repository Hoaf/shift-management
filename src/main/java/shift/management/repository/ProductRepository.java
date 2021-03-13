package shift.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shift.management.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
