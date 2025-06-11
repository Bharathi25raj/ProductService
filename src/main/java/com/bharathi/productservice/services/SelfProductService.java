package com.bharathi.productservice.services;

import com.bharathi.productservice.exceptions.InvalidProductIdException;
import com.bharathi.productservice.exceptions.ProductNotFoundException;
import com.bharathi.productservice.mappers.ProductMapper;
import com.bharathi.productservice.models.Category;
import com.bharathi.productservice.models.Product;
import com.bharathi.productservice.repositories.CategoryRepository;
import com.bharathi.productservice.repositories.ProductRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//We can annotate with @Primary to make this a primary service when there are multiple implementations of ProductService
//Or by giving a name to the service and in controller adding @Qualifier in the constructor
@Service("selfProductService")
@Primary
public class SelfProductService implements ProductService {

    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;
    private ProductMapper productMapper;

//    @Autowired
//    private ProductMapper productMapper;

    public SelfProductService(ProductRepository productRepository, CategoryRepository categoryRepository, ProductMapper productMapper){
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.productMapper = productMapper;
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

        //Commenting out below lines of code, as we have defined cascading type in Product Class,
        //which will auto save the category object before saving product
        /*
        Category category = product.getCategory();

        if(category.getId() == null){
            Category savedCategory = categoryRepository.save(category);
            product.setCategory(savedCategory);
        }
        */

        //Adding this logic to use the category if it already exists
        Category category = product.getCategory();
        if(category != null && category.getId() != null){
            if(categoryRepository.findById(category.getId()).isEmpty()){
                throw new RuntimeException("Category not found with id: " + category.getId());
            } else {
                //Attach the existing category to the current persistence context
                Category existingCategory = categoryRepository.findById(category.getId()).get();
                product.setCategory(existingCategory);
            }
        }

        return productRepository.save(product);
    }

    @Override
    public Product replaceProduct(Long id, Product product) throws ProductNotFoundException {
        if(!productRepository.existsById(id)){
            throw new ProductNotFoundException("Product not found with a id", id);
        }
        product.setId(id);
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Long id, Product product) throws Exception {

        Optional<Product> optionalProduct = productRepository.findProductById(id);

        if(optionalProduct.isEmpty()){
            throw new ProductNotFoundException("Product not found with a id", id);
        }

        Product existingProduct = optionalProduct.get();

        productMapper.updateProduct(product, existingProduct);

        //Not recommended to have so many null checks rather use mapper
        /*

        if(product.getTitle() != null){
            currentProduct.setTitle(product.getTitle());
        }

        if(product.getDescription() != null){
            currentProduct.setDescription(product.getDescription());
        }

        if(product.getPrice() != null){
            currentProduct.setPrice(product.getPrice());
        }

        */

        return productRepository.save(existingProduct);
    }

    @Override
    public void deleteProduct(Long id) throws ProductNotFoundException {

        if(!productRepository.existsById(id)){
            throw new ProductNotFoundException("Product not found with a id", id);
        }

        productRepository.deleteById(id);
    }
}
