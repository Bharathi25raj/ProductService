package com.bharathi.productservice.services;

import com.bharathi.productservice.dtos.FakeStoreProductDto;
import com.bharathi.productservice.models.Category;
import com.bharathi.productservice.models.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class FakeStoreProductService implements ProductService {

    private RestTemplate restTemplate;

    public FakeStoreProductService(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }


    @Override
    public Product getProductById(long id) {
        FakeStoreProductDto fakeStoreProductDto =
                restTemplate.getForObject("https://fakestoreapi.com/products/" + id, FakeStoreProductDto.class);

        return fakeStoreProductDto == null ? null : convertFakeStoreProductDtoToProduct(fakeStoreProductDto);
    }

    public Product convertFakeStoreProductDtoToProduct(FakeStoreProductDto fakeStoreProductDto) {

        Product product = new Product();
        product.setId(fakeStoreProductDto.getId());
        product.setTitle(fakeStoreProductDto.getTitle());
        product.setDescription(fakeStoreProductDto.getDescription());
        product.setPrice(fakeStoreProductDto.getPrice());
        product.setImage(fakeStoreProductDto.getImage());

        Category category = new Category();
        category.setTitle(fakeStoreProductDto.getCategory());

        product.setCategory(category);

        return product;
    }

    @Override
    public List<Product> getAllProducts() {

        //This is not going to work, as in generics, all the types are converted to object types during run time,
        //and the list of objects returned by the fakestore api cannot be mapped to FakeStoreProductDto
        //List<FakeStoreProductDto> fakeStoreProductDtoList =
                //restTemplate.getForObject("https://fakestoreapi.com/products", List.class);


        //here the FakeStoreProductDto[] itself is a type, no generics involved here
        FakeStoreProductDto[] fakeStoreProductDtos =
                restTemplate.getForObject("https://fakestoreapi.com/products", FakeStoreProductDto[].class);

        if(fakeStoreProductDtos == null){
            return null;
        }

        List<Product> products = new ArrayList<>();

        for(FakeStoreProductDto fakeStoreProductDto : fakeStoreProductDtos) {
            products.add(convertFakeStoreProductDtoToProduct(fakeStoreProductDto));
        }

        return products;
    }

    @Override
    public Product addProduct(Product product) {
        return null;
    }

    @Override
    public Product replaceProduct(long id, Product product) {
        return null;
    }

    @Override
    public Product updateProduct(long id, Product product) {
        return null;
    }

    @Override
    public void deleteProduct(long id) {

    }
}
