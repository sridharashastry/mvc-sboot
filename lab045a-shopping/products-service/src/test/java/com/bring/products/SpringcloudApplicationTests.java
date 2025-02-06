package com.bring.products;

import com.bring.products.dto.ProductRequest;
import com.bring.products.models.Product;
import com.bring.products.repo.ProductRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class SpringcloudApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private ProductRepository productRepository;

	@Test
	void shouldCreateProduct() throws Exception {

		System.out.println("BringLabs : Executing Test Cases ");
		// Prepare a sample product request
		ProductRequest productRequest = getProductRequest();
		String productRequestString = objectMapper.writeValueAsString(productRequest);

		// Perform POST request to create a product
		mockMvc.perform(post("/api/product")
						.contentType(MediaType.APPLICATION_JSON)
						.content(productRequestString))
				.andExpect(status().isCreated());

		// Verify that the product was saved in the database
		Optional<Product> savedProduct = productRepository.findByName("Iphone");

		assertThat(savedProduct).isPresent();
		assertThat(savedProduct.get().getDescription()).isEqualTo("Iphone 15");
		assertThat(savedProduct.get().getPrice()).isEqualByComparingTo(BigDecimal.valueOf(12000));

		// Clean up the database by deleting the created product
		savedProduct.ifPresent(product -> productRepository.delete(product));

		System.out.println("BringLabs : Cleaned up the data as well  ");


	}

	private ProductRequest getProductRequest() {
		return ProductRequest.builder()
				.name("Iphone")
				.description("Iphone 15")
				.price(BigDecimal.valueOf(12000))
				.build();
	}
}
