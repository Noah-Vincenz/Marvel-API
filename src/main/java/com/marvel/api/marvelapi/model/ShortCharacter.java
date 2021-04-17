package com.marvel.api.marvelapi.model;

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
