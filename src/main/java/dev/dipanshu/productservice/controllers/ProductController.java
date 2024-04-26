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
