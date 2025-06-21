package com.bharathi.productservice.repositories;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductRepositoryTest {

    /*
    Testing the ProductRepository directly is usually done with integration tests,
    not unit tests — because JpaRepository methods are already tested by Spring Data JPA.
    You only need to test it if you:

    When to Test ProductRepository
    You should test ProductRepository if:
    You’ve written custom query methods (@Query, native SQL, etc.).
    You want to verify that the repository interacts correctly with the DB schema
    (especially with complex relationships or filters).
    You want to test real persistence (like saving/fetching) using an in-memory database.
    */

    @Test
    void queryToFindProductById() {
    }

    @Test
    void queryToFindListOfProducts() {
    }

    @Test
    void queryToGetAProductByPassingTheIdAsInput() {
    }

    @Test
    void queryToGetProductListUsingProjections() {
    }

    @Test
    void queryToFetchProductUsingNativeQuery() {
    }

    @Test
    void queryToGetProductListUsingProjectionsWithNativeQuery() {
    }

    @Test
    void queryToGetAProductByPassingTheIdAsInputWithNativeQuery() {
    }
}