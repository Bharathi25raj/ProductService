package com.bharathi.productservice.advices;

import com.bharathi.productservice.dtos.ArithmeticExceptionDto;
import com.bharathi.productservice.dtos.ArrayIndexOutOfBoundExceptionDto;
import com.bharathi.productservice.dtos.InvalidProductIdDto;
import com.bharathi.productservice.exceptions.InvalidProductIdException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler(ArithmeticException.class)
    public ResponseEntity<ArithmeticExceptionDto> handleArithmeticException(){
        ArithmeticExceptionDto dto = new ArithmeticExceptionDto();
        dto.setMessage("Something went wrong");
        dto.setDetail("divide by zero is not allowed");
        return new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(ArrayIndexOutOfBoundsException.class)
    public ResponseEntity<ArrayIndexOutOfBoundExceptionDto> handleArrayIndexOutOfBoundException(ArrayIndexOutOfBoundsException e){
        ArrayIndexOutOfBoundExceptionDto dto = new ArrayIndexOutOfBoundExceptionDto();
        dto.setDetail("Please pass values within the given size limit " + e.getMessage());
        dto.setMessage("Array Index Out Of Bound");
        return new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(InvalidProductIdException.class)
    public ResponseEntity<InvalidProductIdDto> handleInvalidProductIdException(InvalidProductIdException e){
        InvalidProductIdDto dto = new InvalidProductIdDto();
        dto.setMessage(e.getMessage());
        dto.setId(e.getId());
        return new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);
    }
}
