package dev.dipanshu.productservice.controllers;

import dev.dipanshu.productservice.dtos.CreateProductRequestDto;
import dev.dipanshu.productservice.dtos.ErrorDto;
import dev.dipanshu.productservice.models.Product;
import dev.dipanshu.productservice.services.FakeStoreProductService;
import dev.dipanshu.productservice.services.ProductService;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> responseData = productService.getProducts();

        ResponseEntity<List<Product>> responseEntity =
                new ResponseEntity<>(responseData, HttpStatusCode.valueOf(202));
        return responseEntity;
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
    public Product updateProductPut(@PathVariable("id") Long id, @RequestBody Product product){
        return productService.updateProductPut(id, product);
    }

    @PatchMapping("/products/{id}")
    public Product updateProductPatch(@PathVariable("id") Long id, @RequestBody Product product){
        return productService.updateProductPatch(id, product);
    }

    public void deleteProduct(Long id){

    }

    /*
    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ErrorDto> handleNullPointerException(){
        ErrorDto errorDto = new ErrorDto();
        errorDto.setMessage("Something went wrong. Please try again!");

        return new ResponseEntity<>(errorDto, HttpStatusCode.valueOf(404));
    }

     */
}
