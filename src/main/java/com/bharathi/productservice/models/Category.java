package com.bharathi.productservice.models;



import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@Entity
public class Category extends BaseModel {

    private String title;

    //@OneToMany(mappedBy = "category", cascade = {CascadeType.REMOVE}, fetch = FetchType.EAGER)

    //Lazy by default
    @JsonManagedReference
    @OneToMany(mappedBy = "category", cascade = {CascadeType.REMOVE})
    private List<Product> products;

}
