package com.marvel.api.marvelapi.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="CHARACTER_IDS")
public class CharacterIdEntity {
    @Id
    @Column(name = "ID")
    private int id;

    public CharacterIdEntity setId(int id) {
        this.id = id;
        return this;
    }
}
