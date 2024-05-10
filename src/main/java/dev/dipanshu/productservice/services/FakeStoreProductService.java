package dev.dipanshu.productservice.services;

import dev.dipanshu.productservice.dtos.FakeStoreProductDto;
import dev.dipanshu.productservice.models.Category;
import dev.dipanshu.productservice.models.Product;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service("fakeStoreService")
public class FakeStoreProductService implements ProductService{
    private RestTemplate restTemplate;
    private RedisTemplate<String, Object> redisTemplate;

    public FakeStoreProductService(RestTemplate restTemplate, RedisTemplate<String, Object> redisTemplate){

        this.restTemplate = restTemplate;
        this.redisTemplate = redisTemplate;

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

        // First fetch from REDIS
        Product productFromRedis = (Product) redisTemplate.opsForValue()
                .get(String.valueOf(id));

        // Check if REDIS has it or not
        if(productFromRedis != null){
            return productFromRedis;
        }




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

        // SAVE it to REDIS before returning it
        redisTemplate.opsForValue().set(String.valueOf(id), product);

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
    public Product updateProductPut(Long id, FakeStoreProductDto fakeStoreProductDto) {
        String url = "https://fakestoreapi.com/products/{id}";
        HttpEntity<FakeStoreProductDto> requestEntity = new HttpEntity<FakeStoreProductDto>(fakeStoreProductDto);
        ResponseEntity<FakeStoreProductDto> responseEntity = restTemplate.exchange(
                url,
                HttpMethod.PUT,
                requestEntity,
                FakeStoreProductDto.class,
                id
        );
        return responseEntity.getBody().toProduct();
    }

    @Override
    public Product updateProductPatch(Long id, FakeStoreProductDto fakeStoreProductDto) {
        String url = "https://fakestoreapi.com/products/{id}";

        HttpEntity<FakeStoreProductDto> requestEntity = new HttpEntity<FakeStoreProductDto>(fakeStoreProductDto);

        ResponseEntity<FakeStoreProductDto> responseEntity = restTemplate.exchange(
                url,
                HttpMethod.PUT,
                requestEntity,
                FakeStoreProductDto.class,
                id
        );
        
        return responseEntity.getBody().toProduct();
    }

    @Override
    public Product deleteProduct(Long id) {
        String url = "https://fakestoreapi.com/products/{id}";
        ResponseEntity<FakeStoreProductDto> responseEntity = restTemplate.exchange(
                url,
                HttpMethod.DELETE,
                null,
                FakeStoreProductDto.class,
                id
        );
        return responseEntity.getBody().toProduct();
    }

    @Override
    public List<Product> getProductsByCategory(String category) {
        FakeStoreProductDto[] response = restTemplate
                .getForObject("https://fakestoreapi.com/products/category/" + category,
                        FakeStoreProductDto[].class);

        List<Product> products = new ArrayList<>();

        for(FakeStoreProductDto dto : response) {
            products.add(dto.toProduct());
        }

        return products;
    }
}
