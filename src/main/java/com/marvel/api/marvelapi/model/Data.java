package com.marvel.api.marvelapi.model;

import java.util.List;

/**
 * This POJO class represents a Data object from the Marvel API (see https://developer.marvel.com/docs).
 */
public class Data {
    public int offset;
    public int limit;
    public int total;
    public int count;
    public List<Character> results;
}
