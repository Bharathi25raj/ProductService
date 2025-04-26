package com.bharathi.productservice.repositories;

import com.bharathi.productservice.models.Category;
import com.bharathi.productservice.models.Product;
import com.bharathi.productservice.repositories.projections.ProductWithIdAndTitle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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


    //************************CUSTOM QUERIES***************************

    //HQL Queries
    @Query("select p from Product p where p.id=3")
    Product queryToFindProductById();

    @Query("select p from Product  p where p.price > 100 and lower(p.title) like '%SSD%'")
    List<Product> queryToFindListOfProducts();

    @Query("select p from Product p where p.id=:id")
    Product queryToGetAProductByPassingTheIdAsInput(@Param("id") Long id);

    //Need Projections to fetch only particular attributes of project instead of complete model obj
    @Query("select p.id as id, p.title as title from Product p where p.price>100")
    List<ProductWithIdAndTitle> queryToGetProductListUsingProjections();


    //Native Query
    @Query(value = "select * from product p where p.id = 60", nativeQuery = true)
    Product queryToFetchProductUsingNativeQuery();

    @Query(value = "select p.id as id, p.title as title from product p where p.price>100", nativeQuery = true)
    List<ProductWithIdAndTitle> queryToGetProductListUsingProjectionsWithNativeQuery();

    @Query(value = "select * from product p where p.id=:id", nativeQuery = true)
    Product queryToGetAProductByPassingTheIdAsInputWithNativeQuery(@Param("id") Long id);

}
