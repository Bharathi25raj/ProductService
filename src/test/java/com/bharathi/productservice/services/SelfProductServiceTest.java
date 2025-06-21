package com.bharathi.productservice.services;

import com.bharathi.productservice.repositories.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SelfProductServiceTest {

    @Autowired
    private ProductService productService;

    @MockitoBean
    private ProductRepository productRepository;

    //Testing the Service layer is very similar to testing the Controller layer
    @Test
    void addProduct() {
    }

    @Test
    void updateProduct() {
    }

    @Test
    void deleteProduct() {
    }
}