package shift.management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shift.management.entity.Product;
import shift.management.model.ShiftModel;
import shift.management.service.ProductService;
import shift.management.util.Constant;
import shift.management.util.URL;

import java.util.List;

@RestController
@RequestMapping(URL.API)
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping("/save")
    public ResponseEntity<?> updateOrCreate (@RequestBody Product product){
        return ResponseEntity.ok(productService.save(product));
    }

    @GetMapping("/product")
    public ResponseEntity<?> updateOrCreate (@RequestParam Long id){
        return ResponseEntity.ok(productService.findById(id));
    }

    @GetMapping("/products")
    public ResponseEntity<?> updateOrCreate (){
        return ResponseEntity.ok(productService.findAll());
    }
}
