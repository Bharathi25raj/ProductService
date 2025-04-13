package com.bharathi.productservice.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InvalidProductIdException extends Exception {

    private Long id;

    public InvalidProductIdException(String message, Long id){
        super(message);
        this.id = id;
    }
}
