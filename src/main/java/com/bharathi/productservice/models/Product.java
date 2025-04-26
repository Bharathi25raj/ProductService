package com.bharathi.productservice.models;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
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

    //Eager by default
    @JsonManagedReference
    @ManyToOne(cascade = {CascadeType.PERSIST})
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
