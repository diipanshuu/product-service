package dev.dipanshu.productservice.repositories;

import dev.dipanshu.productservice.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long>{
    Product save(Product entity);

    Product findByIdIs(Long id);

    List<Product> findAll();

    /* Hibernate Query Language
    * @Query("select * from Product where id = 1")
    * Product getTheBestProduct();
    */
}
