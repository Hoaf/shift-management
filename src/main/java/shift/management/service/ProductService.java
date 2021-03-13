package shift.management.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shift.management.entity.Product;
import shift.management.repository.ProductRepository;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public Product save(Product product){
        return productRepository.findById(productRepository.save(product).getId()).orElse(null);
    }

    public Product findById(Long id){
        return productRepository.findById(id).orElse(null);
    }

    public List<Product> findAll(){
        return productRepository.findAll();
    }
}
