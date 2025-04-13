package com.bharathi.productservice.models;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class Product {

    private Long id;
    private String title;
    private String description;
    private Category category;
    private Double price;
    private String image;

}
