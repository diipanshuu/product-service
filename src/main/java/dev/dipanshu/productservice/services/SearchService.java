package dev.dipanshu.productservice.services;

import dev.dipanshu.productservice.models.Product;
import dev.dipanshu.productservice.repositories.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SearchService {

    private ProductRepository productRepository;
    public SearchService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Page<Product> searchProducts(String query, int pageNumber, int pageSize) {
        Sort sort = Sort.by(Sort.Direction.DESC, "category_id")
                .and(Sort.by(Sort.Direction.ASC, "title"));
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);

        return productRepository.findByTitleContaining(query, pageable);
    }
}
