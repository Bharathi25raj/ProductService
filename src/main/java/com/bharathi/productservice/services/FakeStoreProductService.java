package com.bharathi.productservice.services;

import com.bharathi.productservice.dtos.FakeStoreProductDto;
import com.bharathi.productservice.models.Category;
import com.bharathi.productservice.models.Product;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpMessageConverterExtractor;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
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

    public FakeStoreProductDto convertProductToFakeStoreProductDto(Product product){
        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();
        fakeStoreProductDto.setId(product.getId());
        fakeStoreProductDto.setTitle(product.getTitle());
        fakeStoreProductDto.setDescription(product.getDescription());
        fakeStoreProductDto.setPrice(product.getPrice());
        fakeStoreProductDto.setImage(product.getImage());

        if(product.getCategory() == null){
            fakeStoreProductDto.setCategory(null);
        } else {
            fakeStoreProductDto.setCategory(product.getCategory().getTitle());
        }

        return fakeStoreProductDto;
    }

    @Override
    public Product replaceProduct(long id, Product product) {

        //cannot use put method from rest template here because put returns null, but our code wants to return a Product
        //restTemplate.put();

        //convert product to fake store product dto to pass a request body
        FakeStoreProductDto requestDto = convertProductToFakeStoreProductDto(product);

        //In that case, we are using the low level code of rest template, a method called execute which returns something
        RequestCallback requestCallback = restTemplate.httpEntityCallback(requestDto, FakeStoreProductDto.class);
        HttpMessageConverterExtractor<FakeStoreProductDto> responseExtractor =
                new HttpMessageConverterExtractor<>(FakeStoreProductDto.class,
                restTemplate.getMessageConverters());
        FakeStoreProductDto responseDto =
                restTemplate.execute("https://fakestoreapi.com/products/" + id, HttpMethod.PUT, requestCallback, responseExtractor);

        return convertFakeStoreProductDtoToProduct(responseDto);
    }

    @Override
    public Product updateProduct(long id, Product product) {

        //convert product to fake store product dto to pass a request body
        FakeStoreProductDto requestDto =
                restTemplate.getForObject("https://fakestoreapi.com/products/" + id, FakeStoreProductDto.class);


        if(requestDto == null){
            return null;
        }

        if(product.getTitle() != null){
            requestDto.setTitle(product.getTitle());
        }

        if(product.getDescription() != null){
            requestDto.setDescription(product.getDescription());
        }

        if(product.getPrice() != null){
            requestDto.setPrice(product.getPrice());
        }

        if(product.getImage() != null){
            requestDto.setImage(product.getImage());
        }

        if(product.getCategory() != null && product.getCategory().getTitle() != null){
            requestDto.setCategory(product.getCategory().getTitle());
        }


        RequestCallback requestCallback = restTemplate.httpEntityCallback(requestDto, FakeStoreProductDto.class);
        HttpMessageConverterExtractor<FakeStoreProductDto> responseExtractor =
                new HttpMessageConverterExtractor<>(FakeStoreProductDto.class,
                        restTemplate.getMessageConverters());
        FakeStoreProductDto responseDto =
                restTemplate.execute("https://fakestoreapi.com/products/" + id, HttpMethod.PATCH, requestCallback, responseExtractor);

        return convertFakeStoreProductDtoToProduct(responseDto);
    }

    @Override
    public void deleteProduct(long id) {

    }
}
