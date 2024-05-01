package dev.dipanshu.productservice;

import dev.dipanshu.productservice.models.Category;
import dev.dipanshu.productservice.models.Product;
import dev.dipanshu.productservice.repositories.CategoryRepository;
import dev.dipanshu.productservice.repositories.ProductRepository;
import dev.dipanshu.productservice.repositories.projections.ProductWithTitleAndId;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@SpringBootTest
class ProductServiceApplicationTests {
	/*
	@Autowired
	ProductRepository productRepository;
	@Autowired
	CategoryRepository categoryRepository;

	@Test
	void contextLoads() {
	}

	@Test
	public void testingQuery() {
		Product product = productRepository.getProductWithASpecificTitleAndId("luxiry-smartphones", 2L);
		System.out.println(product.getTitle());
	}

	@Test
	public void testingQuery1() {
		ProductWithTitleAndId product = productRepository.getProductWithASpecificTitleAndId1("luxiry-smartphones", 2L);
		System.out.println(product.getId());
		System.out.println(product.getTitle());
	}

	@Test
	@Transactional
	public void testingFetchTypes() {
		Category category = categoryRepository.findByTitle("luxiry-smartphones");
		System.out.println(category.getProducts());
	}

	@Test
	public void testingFetchTypes2() {
		Category category = categoryRepository.findByTitle("Fast Fashion");
 		System.out.println(category.getTitle());
	}


	@Test
	public void testingFetchTypes3() {
		Optional<Category> category = categoryRepository.findById(2L);
		System.out.println(category.get().getTitle());
	}

	@Test
	@Transactional
	public void nPlus1Problem() {
		List<Category> categories = categoryRepository.findAll();
		for (Category category: categories) {
			for (Product product: category.getProducts()) {
				System.out.println(product.getTitle());
			}
		}
	}
	*/
}
