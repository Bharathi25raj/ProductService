package com.bharathi.productservice.controllers;

import com.bharathi.productservice.exceptions.InvalidProductIdException;
import com.bharathi.productservice.exceptions.ProductNotFoundException;
import com.bharathi.productservice.models.Product;
import com.bharathi.productservice.services.ProductService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@SpringBootTest
class ProductControllerTest {

    @Autowired
    private ProductController productController;

    @MockitoBean
    private ProductService productService;

    @Test
    void getProductByIdValidCase() throws Exception {

        //Arrange
        Product product = new Product();
        product.setId(10L);
        product.setTitle("IPhone");
        product.setPrice(150000.0);

        when(productService.getProductById(10L)).thenReturn(product);

        //Act
        ResponseEntity<Product> expectedProduct = productController.getProductById(10L);

        //Assert
        assertEquals(product, expectedProduct.getBody());
        assertEquals(HttpStatus.OK, expectedProduct.getStatusCode());

    }

    @Test
    void getProductByIdInvalidCase() throws Exception {

        when(productService.getProductById(1000L))
                .thenThrow(new InvalidProductIdException("Invalid Product Id", 1000L));

        assertThrows(InvalidProductIdException.class, () -> productController.getProductById(1000L));
    }

    @Test
    void getAllProducts() {

        List<Product> products = new ArrayList<>();

        Product p1 = new Product();
        p1.setId(1L);
        p1.setTitle("IPhone 15");
        p1.setPrice(60000.0);

        Product p2 = new Product();
        p2.setId(2L);
        p2.setTitle("Macbook pro");
        p2.setPrice(100000.0);

        products.add(p1);
        products.add(p2);

        when(productService.getAllProducts()).thenReturn(products);

        assertEquals(products, productController.getAllProducts("token").getBody());

    }

    @Test
    void replaceProduct() throws ProductNotFoundException {

        Product product = new Product();
        product.setTitle("Audi");
        product.setPrice(10000000.0);

        Product savedProduct = new Product();
        savedProduct.setId(10L);
        savedProduct.setTitle("Audi");
        savedProduct.setPrice(10000000.0);

        when(productService.replaceProduct(10L, product)).thenReturn(savedProduct);

        assertEquals(savedProduct, productController.replaceProduct(10L, product).getBody());
    }


    @Test
    void updateProduct() throws Exception {

        Product product = new Product();
        product.setTitle("Samsung S23");
        product.setPrice(150000.0);

        Product updatedProduct = new Product();
        updatedProduct.setId(2L);
        updatedProduct.setTitle("Samsung S23");
        updatedProduct.setPrice(150000.0);

        when(productService.updateProduct(2L, product)).thenReturn(updatedProduct);

        assertEquals(updatedProduct, productController.updateProduct(2L, product).getBody());
    }

    @Test
    void createProduct() {

        Product product = new Product();
        product.setTitle("IPhone 16 pro max");
        product.setPrice(160000.0);
        product.setImage("https://dummyimage.com/");

        Product newProduct = new Product();
        newProduct.setId(10L);
        newProduct.setTitle("IPhone 16 pro max");
        newProduct.setPrice(160000.0);
        newProduct.setImage("https://dummyimage.com/");

        when(productService.addProduct(product)).thenReturn(newProduct);

        assertEquals(newProduct, productController.createProduct(product).getBody());
        assertEquals(HttpStatus.CREATED, productController.createProduct(product).getStatusCode());

    }

    @Test
    void deleteProduct() throws ProductNotFoundException {

        ResponseEntity<Void> response = productController.deleteProduct(5L);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());

        //This doesn't work for method returning void
        //when(productService.deleteProduct(10L))
        // .thenThrow(new ProductNotFoundException("Product Not Found with ID", 10L));

        doThrow(new ProductNotFoundException("Product not found with ID", 10L))
                .when(productService).deleteProduct(10L);

        assertThrows(ProductNotFoundException.class, () -> productController.deleteProduct(10L));
    }
}