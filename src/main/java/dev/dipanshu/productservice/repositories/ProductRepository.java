package dev.dipanshu.productservice.repositories;

import dev.dipanshu.productservice.models.Category;
import dev.dipanshu.productservice.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long>{
    Product save(Product entity);

    Product findByIdIs(Long id);

    /*
    *   `List<Product> findAll()` is a method provided by Spring Data JPA repositories,
    *   such as `JpaRepository`. It automatically retrieves a list of all `Product` records from
    *   the `Product` entity table in the database. This predefined method efficiently handles data
    *   retrieval, returning a list of `Product` objects representing all products stored in the database.
    */
    List<Product> findAll();

    /* Hibernate Query Language
    * @Query("select * from Product where id = 1")
    * Product getTheBestProduct();
    */

    // Delete method to delete a product by id
    void deleteById(Long id);
    List<Product> findByCategory(Category category);
}
