package com.marvel.api.marvelapi.model;

import java.util.List;

/**
 * This POJO class represents a Character object from the Marvel API (see https://developer.marvel.com/docs),
 * maintaining all the character's attributes.
 */
public class Character {
    public Integer id;
    public String name;
    public String description;
    public String modified;
    public Thumbnail thumbnail;
    public String resourceURI;
    public ComicsSeriesStoriesEvents comics;
    public ComicsSeriesStoriesEvents series;
    public ComicsSeriesStoriesEvents stories;
    public ComicsSeriesStoriesEvents events;
    public List<Url> urls;
}