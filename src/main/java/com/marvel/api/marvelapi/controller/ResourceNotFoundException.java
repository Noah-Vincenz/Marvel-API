package com.marvel.api.marvelapi.controller;

/**
 * This class represents a custom exception that is thrown when a requested resource could not be found.
 */
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}