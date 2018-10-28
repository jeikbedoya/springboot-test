package com.jeikode.test.tdd.product;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {

    @InjectMocks
    private ProductService productService;

    @Mock
    private ProductRepository productRepository;

    @Test
    public void getProduct_returnProductDetails() {
        given(productRepository.findById(1L)).willReturn(java.util.Optional.of(new Product(1L, "test", 100_000L)));

        Product product = productService.getProductById(1L);
        assertThat(product.getId()).isEqualTo(1L);
        assertThat(product.getName()).isEqualTo("test");
        assertThat(product.getPrice()).isEqualTo(100_000L);
    }



    @Test(expected = ProductNotFoundException.class)
    public void getProduct_returnNotFound() {

        productService.getProductById(1L);
    }

    @Test
    public void createProduct_returnNewProduct() {
        Product product = new Product();
        product.setId(1L);
        product.setName("test");
        product.setPrice(100_000L);

        given(productRepository.save(any())).willReturn(product);

        Product productCreated = productService.createProduct(product);

        assertThat(productCreated.getId()).isEqualTo(1L);
        assertThat(productCreated.getName()).isEqualTo("test");
        assertThat(productCreated.getPrice()).isEqualTo(100_000L);

    }

    @Test
    public void updateProduct_returnProductUpdated() {
        Product product = new Product();
        product.setId(1L);
        product.setName("test");
        product.setPrice(100_000L);

        given(productRepository.findById(anyLong())).willReturn(java.util.Optional.ofNullable(product));
        product.setName("update");
        given(productRepository.save(product)).willReturn(product);
        Product productUpdated = productService.updateProduct(1L, product);

        assertThat(productUpdated).isNotNull();
        assertThat(productUpdated.getId()).isEqualTo(1L);
        assertThat(productUpdated.getName()).isEqualTo("update");
        assertThat(productUpdated.getPrice()).isEqualTo(100_000L);
    }
}