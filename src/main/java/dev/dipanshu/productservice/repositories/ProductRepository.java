package dev.dipanshu.productservice.repositories;

import dev.dipanshu.productservice.models.Category;
import dev.dipanshu.productservice.models.Product;
import dev.dipanshu.productservice.repositories.projections.ProductWithTitleAndId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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

    // HQL Query
    @Query("select p from Product p where p.category.title = :title and p.id = :id")
    Product getProductWithASpecificTitleAndId(@Param("title") String title, @Param("id") Long id);

    // Native SQL Query
    @Query(value = "select * from product where id = :id and title = :title", nativeQuery = true)
    Product getProductWithSomeTitleAndId(@Param("title") String title, @
        Param("id") Long id);


    @Query("select p.id, p.title from Product p where p.category.title = :title and p.id = :id")
    ProductWithTitleAndId getProductWithASpecificTitleAndId1(@Param("title") String title, @Param("id") Long id);

    // Delete method to delete a product by id
    void deleteById(Long id);
    List<Product> findByCategory(Category category);

    Page<Product> findByTitleContaining(String title, Pageable pageable);
}
