package dev.dipanshu.productservice.services;

import dev.dipanshu.productservice.dtos.FakeStoreProductDto;
import dev.dipanshu.productservice.dtos.FakeStoreProductDtoList;
import dev.dipanshu.productservice.models.Category;
import dev.dipanshu.productservice.models.Product;
import org.springframework.core.ParameterizedTypeReference;
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
        FakeStoreProductDto fakeStoreProductDto = restTemplate
                .getForObject("https://fakestoreapi.com/products/" + id,
                        FakeStoreProductDto.class);

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
//        FakeStoreProductDtoList fakeStoreProductDtoList = restTemplate
//                .getForObject("https://fakestoreapi.com/products",
//                        FakeStoreProductDtoList.class);
//
//        List<FakeStoreProductDto> dtoList = fakeStoreProductDtoList.getProducts();
//        List<Product> products = new ArrayList<>();
        ResponseEntity<List<FakeStoreProductDto>> responseEntity = restTemplate.exchange(
                "https://fakestoreapi.com/products",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<FakeStoreProductDto>>() {}
        );

        List<FakeStoreProductDto> dtoList = responseEntity.getBody();
        List<Product> products = new ArrayList<>();

        for(FakeStoreProductDto dto : dtoList) {

            Product product = new Product();
            product.setId(dto.getId());
            product.setTitle(dto.getTitle());
            product.setDescription(dto.getDescription());
            product.setImageUrl(dto.getImage());

            Category category = new Category();
            category.setTitle(dto.getCategory());
            product.setCategory(category);

            products.add(product);
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
