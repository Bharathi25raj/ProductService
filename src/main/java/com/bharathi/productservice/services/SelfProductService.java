package com.bharathi.productservice.services;

import com.bharathi.productservice.exceptions.InvalidProductIdException;
import com.bharathi.productservice.exceptions.ProductNotFoundException;
import com.bharathi.productservice.models.Product;
import com.bharathi.productservice.repositories.ProductRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("selfProductService")
public class SelfProductService implements ProductService {

    private ProductRepository productRepository;

    public SelfProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    @Override
    public Product getProductById(Long id) throws Exception {
        Optional<Product> optionalProduct = productRepository.findProductById(id);

        if(optionalProduct.isEmpty()){
            throw new ProductNotFoundException("Product Not Found with the passed ID", id);
        }

        return optionalProduct.get();
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product addProduct(Product product) {
        return null;
    }

    @Override
    public Product replaceProduct(Long id, Product product) {
        return null;
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        return null;
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
