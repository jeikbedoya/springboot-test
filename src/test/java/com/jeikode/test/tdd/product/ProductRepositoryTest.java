package com.jeikode.test.tdd.product;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.*;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ProductRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void getProduct_returnProductDetails() {
        Product product = new Product();
        product.setName("test");
        product.setPrice(100_000L);
        entityManager.persistAndFlush(product);

        Product prodFound = productRepository.findById(1L).get();


        assertThat(prodFound.getId()).isEqualTo(1);
        assertThat(prodFound.getName()).isEqualTo("test");
        assertThat(prodFound.getPrice()).isEqualTo(100_000L);
    }

    @Test
    public void createProduct_returnNewProduct() {
        Product product = new Product();
        product.setName("test");
        product.setPrice(100_000L);

        Product prodSaved = productRepository.save(product);

        assertThat(prodSaved.getId()).isNotNull();
        assertThat(prodSaved.getName()).isEqualTo(product.getName());
        assertThat(prodSaved.getPrice()).isEqualTo(product.getPrice());

    }
}