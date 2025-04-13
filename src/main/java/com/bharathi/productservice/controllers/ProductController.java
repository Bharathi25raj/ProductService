package com.bharathi.productservice.controllers;

import com.bharathi.productservice.models.Product;
import com.bharathi.productservice.services.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;

    ProductController(ProductService productService){
        this.productService = productService;
    }

    @GetMapping("/{id}")
    private Product getProductById(@PathVariable("id") long id){
        return productService.getProductById(id);
    }


    //get all products
    @GetMapping
    public List<Product> getAllProducts(){
        return productService.getAllProducts();
    }

    //Replace a product
    @PutMapping("/{id}")
    public Product replaceProduct(@PathVariable("id") long id, @RequestBody Product product){
        return productService.replaceProduct(id, product);
    }

    //update a product
    @PatchMapping("/{id}")
    public Product updateProduct(@PathVariable("id") long id, @RequestBody Product product){
        return productService.updateProduct(id, product);
    }

    //create a new product
    public Product createProduct(@RequestBody Product product){
        return productService.addProduct(product);
    }

    //delete a product
    public void deleteProduct(@PathVariable("id") long id){
        productService.deleteProduct(id);
    }
}
