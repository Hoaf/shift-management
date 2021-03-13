package shift.management.entity;

import lombok.Data;

import javax.persistence.Entity;

@Entity
@Data
public class Product {
    private Long id;
    private String name;
    private String description;
    private Double price;
}
