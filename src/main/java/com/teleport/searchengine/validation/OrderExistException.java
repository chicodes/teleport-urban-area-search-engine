package com.teleport.searchengine.validation;


import lombok.Data;

@Data
public class OrderExistException extends Exception {
    private final String message;
    public OrderExistException(String message) {
       this.message = message;
    }
}
