package com.bharathi.productservice;

import com.bharathi.productservice.models.Product;
import com.bharathi.productservice.repositories.CategoryRepository;
import com.bharathi.productservice.repositories.ProductRepository;
import com.bharathi.productservice.repositories.projections.ProductWithIdAndTitle;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class ProductServiceApplicationTests {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Test
    void contextLoads() {
    }

    @Test
    void testHQLQuery(){

        Product product = productRepository.queryToFindProductById();
        System.out.println("Title........ " + product.getTitle());

        List<Product> productList = productRepository.queryToFindListOfProducts();
        for(Product p : productList){
            System.out.println("Product price: " + p.getPrice() + ", Product title: " + p.getTitle());
        }

        Product product1 = productRepository.queryToGetAProductByPassingTheIdAsInput(59L);
        System.out.println("Product By Passing ID as Input " + product1.getTitle());

        List<ProductWithIdAndTitle> productWithIdAndTitleList = productRepository.queryToGetProductListUsingProjections();
        for(ProductWithIdAndTitle pwit : productWithIdAndTitleList){
            System.out.println("ProductID: " + pwit.getId() + ", ProductTitle: " + pwit.getTitle());
        }
    }


    @Test
    void testNativeQuery(){
        Product p = productRepository.queryToFetchProductUsingNativeQuery();
        System.out.println("P-ID: " + p.getId() + ", P-Title: " + p.getTitle());

        List<ProductWithIdAndTitle> productWithIdAndTitleList = productRepository.queryToGetProductListUsingProjectionsWithNativeQuery();
        for(ProductWithIdAndTitle pwit : productWithIdAndTitleList){
            System.out.println("ProductID: " + pwit.getId() + ", ProductTitle: " + pwit.getTitle());
        }

        Product product1 = productRepository.queryToGetAProductByPassingTheIdAsInputWithNativeQuery(60L);
        System.out.println("Product By Passing ID as Input Native " + product1.getTitle());
    }


    @Test
    void testDeleteCategory(){
        //Deleting this category id should delete all products
        // associated with this category id as per the cascading type defined in Category model
        categoryRepository.deleteById(102L);
    }

}
