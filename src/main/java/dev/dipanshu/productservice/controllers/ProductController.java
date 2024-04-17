package dev.dipanshu.productservice.controllers;

import dev.dipanshu.productservice.models.Product;
import dev.dipanshu.productservice.services.FakeStoreProductService;
import dev.dipanshu.productservice.services.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

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

    public void deleteProduct(Long id){

    }
}
