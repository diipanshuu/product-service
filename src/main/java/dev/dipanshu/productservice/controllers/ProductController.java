package dev.dipanshu.productservice.controllers;

import dev.dipanshu.productservice.models.Product;
import dev.dipanshu.productservice.services.FakeStoreProductService;
import dev.dipanshu.productservice.services.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

//    ProductService productService = new FakeStoreProductService();
    ProductService productService;
    public ProductController(ProductService productService){
        this.productService = productService;
    }
    @PostMapping("/products")
    public void createProduct(){

    }

    @GetMapping("/products")
    public List<Product> getAllProducts(){
        return productService.getProducts();
    }

    @GetMapping("/products/{id}")
    public Product getProductById(@PathVariable("id") Long id){
//        Product product = new Product();
//        product.getId();
//        product.setTitle("asdas");
        return productService.getSingleProduct(id);
    }

    @GetMapping("/products/categories")
    public List<String> getProductCategories(){
        return productService.getProductCategories();
    }

    @PutMapping("/products/{id}")
    public Product updateProduct(@PathVariable("id") Long id, @RequestBody Product product){
        return productService.updateProduct(id, product);
    }

    public void deleteProduct(Long id){

    }
}
