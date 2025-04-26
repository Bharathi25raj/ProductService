package com.bharathi.productservice.models;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@Entity
public class Category extends BaseModel {

    private String title;

    @OneToMany(mappedBy = "category", cascade = {CascadeType.REMOVE})
    private List<Product> products;

}
