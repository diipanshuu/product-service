package dev.dipanshu.productservice.dtos;

import dev.dipanshu.productservice.models.Category;
import dev.dipanshu.productservice.models.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreProductDto {
    private Long id;
    private String title;
    private String image;
    private String description;
    private String category;
    private double price;

    public Product toProduct(){
        Product product = new Product();
        product.setId(getId());
        product.setTitle(getTitle());
        product.setDescription(getDescription());
        product.setImageUrl(getImage());
        product.setPrice(getPrice());

        Category category = new Category();
        category.setTitle(getCategory());
        product.setCategory(category);

        return product;
    }
}
