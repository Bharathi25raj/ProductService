package com.bharathi.productservice.dtos;

import lombok.Data;

@Data
public class InvalidProductIdDto {
    private Long id;
    private String message;
}
