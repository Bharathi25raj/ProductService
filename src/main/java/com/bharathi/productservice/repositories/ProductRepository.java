package com.bharathi.productservice.repositories;

import com.bharathi.productservice.models.Category;
import com.bharathi.productservice.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findProductById(Long id);

    //No need to declare this method, we can directly call repo.findAll();
    //List<Product> findAll();

    Optional<Product> findProductByTitle(String title);

    Optional<Product> findProductByCategory(Category category);

    List<Product> findProductByTitleAndDescription(String title, String description);

    //Uses LIKE operator in SQL query
    List<Product> findByTitleContainingIgnoreCase(String word);

    //Uses LIMIT keyword in SQL Query
    List<Product> findTopThreeByTitle(String title);

    //Jpa automatically provides this method
    //void deleteById(Long id);

    void deleteByTitle(String title);


    //Jpa automatically provides this method
    //Product save(Product product);

}
