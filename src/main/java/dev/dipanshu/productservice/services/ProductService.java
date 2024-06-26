package dev.dipanshu.productservice.services;

import dev.dipanshu.productservice.dtos.FakeStoreProductDto;
import dev.dipanshu.productservice.models.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
    public Product createProduct(String title, double price,
    String description, String image, String category);
    public Product getSingleProduct(Long id);
    public List<Product> getProducts();
    public List<String> getProductCategories();
    public Product updateProductPut(Long id, FakeStoreProductDto fakeStoreProductDto);
    public Product updateProductPatch(Long id, FakeStoreProductDto fakeStoreProductDto);
    public Product deleteProduct(Long id);
    public List<Product> getProductsByCategory(String category);
}
