package com.marvel.api.marvelapi.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="IDS")
public class CharacterIdEntity {
    @Id
    @Column(name = "ID")
    private final int id;

    public CharacterIdEntity(int id) {
        this.id = id;
    }
}
