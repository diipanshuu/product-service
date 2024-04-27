package dev.dipanshu.productservice.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

            /*

                    The provided code defines a Spring Data JPA entity class Product,
                    representing a table in the database. The class extends BaseModel
                    and includes fields for title, description, price, and imageUrl.
                    It has a many-to-one relationship with the Category entity,
                    as indicated by the @ManyToOne annotation. CascadeType.PERSIST ensures
                    associated Category objects are persisted with the Product.

            */

@Entity
public class Product extends BaseModel{
    private String title;
    private String description;
    private double price;
    private String imageUrl;

    @ManyToOne(cascade = {CascadeType.PERSIST})
    private Category category;


}
