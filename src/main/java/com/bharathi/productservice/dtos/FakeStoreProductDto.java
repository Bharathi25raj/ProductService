package com.bharathi.productservice.dtos;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class FakeStoreProductDto {

    private long id;
    private String title;
    private String description;
    private String category;
    private double price;
    private String image;

}
