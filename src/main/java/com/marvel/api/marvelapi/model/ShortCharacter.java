package com.marvel.api.marvelapi.model;

/**
 * This POJO class represents a shortened form of the Character object from the Marvel API (see https://developer.marvel.com/docs),
 * maintaining only the character's ID, name, description and thumbnail.
 * The constructor allows for creation of an object of this class from a normal Character object.
 */
public class ShortCharacter {
    public Integer id;
    public String name;
    public String description;
    public Thumbnail thumbnail;

    public ShortCharacter(Character character) {
        id = character.id;
        name = character.name;
        description = character.description;
        thumbnail = character.thumbnail;
    }
}
