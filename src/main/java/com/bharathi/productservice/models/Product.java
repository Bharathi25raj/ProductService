package com.bharathi.productservice.models;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Product {

    private long id;
    private String title;
    private String description;
    private Category category;
    private double price;
    private String image;

}
