package com.bharathi.productservice.inheritancerepresentation.tableperclass;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "tpc_ta")
public class TA extends User {
    private Integer noOfSessions;
    private Double avgRating;
}
