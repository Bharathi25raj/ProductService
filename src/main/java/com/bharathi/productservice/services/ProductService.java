package com.bharathi.productservice.services;

import com.bharathi.productservice.models.Product;

import java.util.List;

public interface ProductService {

    Product getProductById(long id);

    List<Product> getAllProducts();

    Product addProduct(Product product);

    Product replaceProduct(long id, Product product);

    Product updateProduct(long id, Product product);

    void deleteProduct(long id);
}
