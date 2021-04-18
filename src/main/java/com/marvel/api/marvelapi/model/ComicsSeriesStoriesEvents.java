package com.marvel.api.marvelapi.model;

import java.util.List;

/**
 * This POJO class represents a Comics, Series, Stories and Events object from the Marvel API (see https://developer.marvel.com/docs).
 */
public class ComicsSeriesStoriesEvents {
    public int available;
    public String collectionURI;
    public List<Item> items;
    public int returned;
}
