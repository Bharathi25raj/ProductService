package com.bharathi.productservice.models;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Setter
@Getter
@Entity
public class Product extends BaseModel {

    private String title;
    private String description;

    @ManyToOne
    private Category category;
    private Double price;
    private String image;

   /*
       1                    1

    Product ------------ Category

       M                     1

       ==> M:1

    */
}
