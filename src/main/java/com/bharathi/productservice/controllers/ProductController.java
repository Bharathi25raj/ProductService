package com.bharathi.productservice.controllers;

import com.bharathi.productservice.commons.AuthenticationCommons;
import com.bharathi.productservice.dtos.Role;
import com.bharathi.productservice.dtos.UserDto;
import com.bharathi.productservice.exceptions.ProductControllerSpecificException;
import com.bharathi.productservice.exceptions.ProductNotFoundException;
import com.bharathi.productservice.models.Product;
import com.bharathi.productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;
    private AuthenticationCommons authenticationCommons;

    public ProductController(@Qualifier("selfProductService") ProductService productService,
                             AuthenticationCommons authenticationCommons){
        this.productService = productService;
        this.authenticationCommons = authenticationCommons;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") Long id) throws Exception {
        Product product = productService.getProductById(id);
        return new ResponseEntity<>(product, HttpStatus.OK);
        //throw new RuntimeException("Product ID not found");
    }

    //get all products
    @GetMapping("/all/{token}")
    public ResponseEntity<List<Product>> getAllProducts(@PathVariable("token") String token){

        try{
            UserDto userDto = authenticationCommons.validateToken(token);
        } catch (HttpClientErrorException.Unauthorized ex){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        /*
        boolean isAdmin = false;

        for(Role role : userDto.getRoles()) {
            if(role.equals("ADMIN")){
                isAdmin = true;
                break;
            }
        }

        if(!isAdmin){
            return null;
        }
        */

        List<Product> products = productService.getAllProducts();
        //return new ResponseEntity<>(products, HttpStatus.OK);
        return ResponseEntity.ok(products);
    }

    //Replace a product
    @PutMapping("/{id}")
    public ResponseEntity<Product> replaceProduct(@PathVariable("id") Long id, @RequestBody Product product) throws ProductNotFoundException {
        Product replacedProduct = productService.replaceProduct(id, product);
        return new ResponseEntity<>(replacedProduct, HttpStatus.OK);
    }

    //update a product
    @PatchMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable("id") Long id, @RequestBody Product product) throws Exception {
        Product updatedProduct = productService.updateProduct(id, product);
        return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
    }

    //create a new product
    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product){
        Product newProduct = productService.addProduct(product);
        return new ResponseEntity<>(newProduct, HttpStatus.CREATED);
    }

    //delete a product
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable("id") Long id) throws ProductNotFoundException {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }


    //Controller Advice - Exception Handler specific to a Controller
    @ExceptionHandler(ProductControllerSpecificException.class)
    public ResponseEntity<String> handleProductControllerSpecificException(ProductControllerSpecificException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

}
