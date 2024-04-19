package dev.dipanshu.productservice.services;

import dev.dipanshu.productservice.ProductServiceApplication;
import dev.dipanshu.productservice.dtos.CreateProductRequestDto;
import dev.dipanshu.productservice.models.Product;

import java.util.List;

public interface ProductService {
    public Product createProduct(String title, double price,
    String description, String image, String category);
    public Product getSingleProduct(Long id);
    public List<Product> getProducts();
    public List<String> getProductCategories();
    public Product updateProductPut(Long id, Product product);
    public Product updateProductPatch(Long id, Product product);
    public Product deleteProduct(Long id);
}
