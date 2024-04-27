package dev.dipanshu.productservice.controllers;

import dev.dipanshu.productservice.dtos.CreateProductRequestDto;
import dev.dipanshu.productservice.dtos.FakeStoreProductDto;
import dev.dipanshu.productservice.models.Product;
import dev.dipanshu.productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

//    ProductService productService = new FakeStoreProductService();
    ProductService productService;
    public ProductController(@Qualifier("selfProductService") ProductService productService){

        this.productService = productService;
    }
    @PostMapping("/products")
    public Product createProduct(@RequestBody CreateProductRequestDto productRequestDto){
          return productService.createProduct(
                  productRequestDto.getTitle(),
                  productRequestDto.getPrice(),
                  productRequestDto.getDescription(),
                  productRequestDto.getImage(),
                  productRequestDto.getCategory()
          );

    }

    /*
    *   When a GET request is made to `/products`, the method `getAllProducts()` is executed.
    *   It calls `productService.getProducts()` to fetch a list of `Product` objects,
    *   The Spring Boot framework automatically serializes this list into JSON format and
    *   returns it as a JSON response to the client. Postman displays the JSON data.
    *   The class SelfProductService in Service package implements ProductService interface,
    *   and we use obj of that. We call getProducts on that which is implemented in the SelfProductService
    *   class.
    */
    @GetMapping("/products")
    public List<Product> getAllProducts() {
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
    public Product updateProductPut(@PathVariable("id") Long id, @RequestBody FakeStoreProductDto fakeStoreProductDto){
        return productService.updateProductPut(id, fakeStoreProductDto);
    }

    @PatchMapping("/products/{id}")
    public Product updateProductPatch(@PathVariable("id") Long id, @RequestBody FakeStoreProductDto fakeStoreProductDto){
        return productService.updateProductPatch(id, fakeStoreProductDto);
    }

    @DeleteMapping("/products/{id}")
    public Product deleteProduct(@PathVariable("id") Long id){
        return productService.deleteProduct(id);
    }

    @GetMapping("/products/category/{category}")
    public List<Product> getProductsByCategory(@PathVariable("category") String category){
        return productService.getProductsByCategory(category);
    }
}
