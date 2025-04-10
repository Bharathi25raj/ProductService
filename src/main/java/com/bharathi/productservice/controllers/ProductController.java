package com.bharathi.productservice.controllers;

import com.bharathi.productservice.models.Product;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    @GetMapping("/{id}")
    private Product getProductById(@PathVariable("id") long id){
        return new Product();
    }

    @GetMapping
    public List<Product> getAllProducts(){
        return new ArrayList<Product>();
    }

    //Replace a product
    @PutMapping("/{id}")
    public Product replaceProduct(@PathVariable("id") long id, @RequestBody Product product){
        return new Product();
    }

    //update a product
    @PatchMapping("/{id}")
    public Product updateProduct(@PathVariable("id") long id, @RequestBody Product product){
        return new Product();
    }

    //create a new product
    public Product createProduct(@RequestBody Product product){
        return new Product();
    }

    //delete a product
    public void deleteProduct(@PathVariable("id") long id){
        return;
    }
}
