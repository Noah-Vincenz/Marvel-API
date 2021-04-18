package com.marvel.api.marvelapi.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * This class represents at DB entity for character IDs and is used to insert entities to the DB.
 */
@Entity
@Table(name="CHARACTER_IDS")
public class CharacterIdEntity {
    @Id
    @Column(name = "ID")
    private int id;

    /**
     * Set this entity's ID and then return this entity.
     * @param id the ID to be set for this entity.
     * @return this object.
     */
    public CharacterIdEntity setId(int id) {
        this.id = id;
        return this;
    }
}
