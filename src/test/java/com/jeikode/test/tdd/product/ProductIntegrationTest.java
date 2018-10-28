package com.jeikode.test.tdd.product;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    private ProductRepository proctRepository;

    @Test
    public void getProduct_returnProductDetails() {
        given(proctRepository.findById(anyLong())).willReturn(java.util.Optional.of(new Product(1L, "test", 100_000L)));

        ResponseEntity<Product> response = restTemplate.getForEntity("/products/1", Product.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().getId()).isEqualTo(1);
        assertThat(response.getBody().getName()).isEqualTo("test");
        assertThat(response.getBody().getPrice()).isEqualTo(100_000);
    }

    @Test
    public void createProduct_returnNewProduct() {

        Product product = new Product();
        product.setName("test");
        product.setPrice(100_000L);

        ResponseEntity<Product> response = restTemplate.postForEntity("/products", product, Product.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().getId()).isEqualTo(1L);
        assertThat(response.getBody().getName()).isEqualTo("test");
        assertThat(response.getBody().getPrice()).isEqualTo(100_000L);
    }

    @Test
    public void updateProduct_returnProductUpdated() {
        Product product = new Product(1L, "test", 100_000L);

        given(proctRepository.save(any())).willReturn(product);

        HttpEntity<Product> httpEntity = new HttpEntity<>(product);
        ResponseEntity<Product> response = restTemplate.exchange("/products", HttpMethod.PUT, httpEntity, Product.class);

        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

    }
}
