package shift.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shift.management.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
