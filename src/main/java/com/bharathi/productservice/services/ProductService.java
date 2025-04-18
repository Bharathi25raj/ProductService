package com.bharathi.productservice.services;

import com.bharathi.productservice.exceptions.ProductNotFoundException;
import com.bharathi.productservice.models.Product;

import java.util.List;

public interface ProductService {

    Product getProductById(Long id) throws Exception;

    List<Product> getAllProducts();

    Product addProduct(Product product);

    Product replaceProduct(Long id, Product product) throws ProductNotFoundException;

    Product updateProduct(Long id, Product product);

    void deleteProduct(Long id);
}
