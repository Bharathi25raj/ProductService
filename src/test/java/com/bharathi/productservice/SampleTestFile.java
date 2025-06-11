package com.bharathi.productservice;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SampleTestFile {

    /*
    Arrange - Create the variables/objects required for testing
    Act - call the required functions
    Assert - check the function output against the expected output
    */

    @Test
    void testOnePlusOneIsTwo(){
        int i = 1+1;  //Arrange + Act
        assert i == 2; //Assert
    }

    @Test
    void testTwoMultiplyByTwoIsFour(){

        //Arrange
        int j = 2;

        //Act
        int k = 2 * 2;

        //Assert
        //assert(k == 4);
        //assertEquals(k, 4);

        assertEquals(k, 4, "2*2 isn't returning correct output");
    }

}
