package com.example.hanguyen.demo;

/**
 * Created by Ha Nguyen on 1/14/2018.
 * Purpose : create a custom exception class for method giaiPTB1
 */

public class PTBNhatException extends Exception {
    public PTBNhatException(String message){
        super(message);
    }
}
