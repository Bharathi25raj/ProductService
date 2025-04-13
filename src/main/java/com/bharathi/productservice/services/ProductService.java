package com.bharathi.productservice.services;

import com.bharathi.productservice.models.Product;

import java.util.List;

public interface ProductService {

    Product getProductById(Long id);

    List<Product> getAllProducts();

    Product addProduct(Product product);

    Product replaceProduct(Long id, Product product);

    Product updateProduct(Long id, Product product);

    void deleteProduct(Long id);
}
