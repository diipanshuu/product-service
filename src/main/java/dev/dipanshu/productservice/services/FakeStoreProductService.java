package dev.dipanshu.productservice.services;

import dev.dipanshu.productservice.dtos.FakeStoreProductDto;
import dev.dipanshu.productservice.models.Category;
import dev.dipanshu.productservice.models.Product;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class FakeStoreProductService implements ProductService{
    private RestTemplate restTemplate;

    public FakeStoreProductService(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    @Override
    public Product createProduct(String title, double price, String description, String image, String category) {
        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();
        fakeStoreProductDto.setCategory(category);
        fakeStoreProductDto.setTitle(title);
        fakeStoreProductDto.setPrice(price);
        fakeStoreProductDto.setDescription(description);
        fakeStoreProductDto.setImage(image);
        FakeStoreProductDto response = restTemplate
                .postForObject("https://fakestoreapi.com/products",
                        fakeStoreProductDto,
                        FakeStoreProductDto.class);

        return response.toProduct();
    }

    @Override
    public Product getSingleProduct(Long id) {
        /*
        FakeStoreProductDto fakeStoreProductDto = restTemplate
                .getForObject("https://fakestoreapi.com/products/" + id,
                        FakeStoreProductDto.class);
         */
        ResponseEntity<FakeStoreProductDto> responseEntity = restTemplate.
                getForEntity(
                        "https://fakestoreapi.com/products/" + id,
                        FakeStoreProductDto.class
                );

        FakeStoreProductDto fakeStoreProductDto = responseEntity.getBody();


        Product product = new Product();
        product.setId(fakeStoreProductDto.getId());
        product.setTitle(fakeStoreProductDto.getTitle());
        product.setDescription(fakeStoreProductDto.getDescription());
        product.setImageUrl(fakeStoreProductDto.getImage());

        Category category = new Category();
        category.setTitle(fakeStoreProductDto.getCategory());
        product.setCategory(category);

        return product;

    }

    public List<Product> getProducts(){
        FakeStoreProductDto[] response = restTemplate
                .getForObject("https://fakestoreapi.com/products",
                        FakeStoreProductDto[].class);

        List<Product> products = new ArrayList<>();

        for(FakeStoreProductDto dto : response) {
            products.add(dto.toProduct());
        }

        return products;
    }

    @Override
    public List<String> getProductCategories() {
        String[] categories = restTemplate.getForObject(
                "https://fakestoreapi.com/products/categories",
                    String[].class);

        return Arrays.asList(categories);
    }

    @Override
    public Product updateProductPut(Long id, Product product) {
        restTemplate.put(
                "https://fakestoreapi.com/products/{id}",
                product,
                id
        );
        return product;
    }

    @Override
    public Product updateProductPatch(Long id, Product product) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Product> requestEntity = new HttpEntity<>(product, headers);

        ResponseEntity<Product> responseEntity = restTemplate.exchange(
                "https://fakestoreapi.com/products/{id}",
                HttpMethod.PATCH,
                requestEntity,
                Product.class,
                id
        );
        
        return responseEntity.getBody();
    }
}
