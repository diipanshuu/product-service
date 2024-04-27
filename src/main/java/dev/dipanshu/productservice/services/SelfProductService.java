package dev.dipanshu.productservice.services;

import dev.dipanshu.productservice.dtos.FakeStoreProductDto;
import dev.dipanshu.productservice.models.Category;
import dev.dipanshu.productservice.models.Product;
import dev.dipanshu.productservice.repositories.CategoryRepository;
import dev.dipanshu.productservice.repositories.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("selfProductService")
public class SelfProductService implements ProductService{

    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;

    public SelfProductService(ProductRepository productRepository, CategoryRepository categoryRepository){
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }
    @Override
    public Product getSingleProduct(Long id) {
        return productRepository.findByIdIs(id);
    }

    @Override
    public Product createProduct(String title, double price, String description, String image, String categoryTitle) {
        Product product = new Product();
        product.setTitle(title);
        product.setDescription(description);
        product.setImageUrl(image);
        product.setPrice(price);

        Category categoryFromDatabase = categoryRepository.findByTitle(categoryTitle);
        if(categoryFromDatabase == null){
            Category newCategory = new Category();
            newCategory.setTitle(categoryTitle);
            categoryFromDatabase = newCategory;
//            categoryFromDatabase = categoryRepository.save(newCategory);
        }

        product.setCategory(categoryFromDatabase);
        return productRepository.save(product);
    }

    /*
    *   The getProducts() method retrieves a list of all Product objects from the database.
    *   It calls productRepository.findAll(), which is an interface called ProductRepository
    *   in repositories package which extends JpaRepository which internally implements the findAll method,
    *   provided by Spring Data JPA, which fetches all records from the Product entity table in the database.
    *   The findAll() method is predefined in Spring Data and automatically handles data retrieval.
    *   The method returns a list of Product objects representing all products stored in the database.
    */
    @Override
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    @Override
    public List<String> getProductCategories() {

        List<Category> allCategories = categoryRepository.findAll();
        List<String> listOfString = new ArrayList<>();
        for(Category obj : allCategories){
            listOfString.add(obj.getTitle());
        }
        return listOfString;
    }


    @Override
    public Product updateProductPut(Long id, FakeStoreProductDto fakeStoreProductDto) {
        Product product = productRepository.findById(id).orElse(new Product());

        product.setId(id);

        product.setTitle(fakeStoreProductDto.getTitle());
        product.setDescription(fakeStoreProductDto.getDescription());
        product.setImageUrl(fakeStoreProductDto.getImage());
        product.setPrice(fakeStoreProductDto.getPrice());

        Category category = categoryRepository.findByTitle(fakeStoreProductDto.getCategory());
        if (category == null) {
            category = new Category();
            category.setTitle(fakeStoreProductDto.getCategory());
            categoryRepository.save(category);
        }
        product.setCategory(category);

        return productRepository.save(product);
    }

    @Override
    public Product updateProductPatch(Long id, FakeStoreProductDto fakeStoreProductDto) {
        Product product = productRepository.findByIdIs(id);
        if (fakeStoreProductDto.getTitle() != null) {
            product.setTitle(fakeStoreProductDto.getTitle());
        }
        if (fakeStoreProductDto.getDescription() != null) {
            product.setDescription(fakeStoreProductDto.getDescription());
        }
        if (fakeStoreProductDto.getImage() != null) {
            product.setImageUrl(fakeStoreProductDto.getImage());
        }
        if (fakeStoreProductDto.getPrice() != 0D) {
            product.setPrice(fakeStoreProductDto.getPrice());
        }
        if (fakeStoreProductDto.getCategory() != null) {
            Category category = categoryRepository.findByTitle(fakeStoreProductDto.getCategory());
            if (category == null) {
                category = new Category();
                category.setTitle(fakeStoreProductDto.getCategory());
                categoryRepository.save(category);
            }
            product.setCategory(category);
        }
        return productRepository.save(product);
    }


    @Override
    public Product deleteProduct(Long id) {
        Product product = productRepository.findByIdIs(id);
        productRepository.deleteById(id);
        return product;
    }


    @Override
    public List<Product> getProductsByCategory(String categoryTitle) {
        Category category = categoryRepository.findByTitle(categoryTitle);
        if (category != null) {
            return productRepository.findByCategory(category);
        }
        return new ArrayList<>();
    }


}
