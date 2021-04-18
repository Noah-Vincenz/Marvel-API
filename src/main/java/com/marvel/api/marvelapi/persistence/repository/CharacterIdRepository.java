package com.marvel.api.marvelapi.persistence.repository;

import com.marvel.api.marvelapi.persistence.entity.CharacterIdEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CharacterIdRepository extends JpaRepository<CharacterIdEntity, Integer> {

    /**
     * Get all the IDs stored in the DB.
     * @return a list of all the IDs stored in the DB.
     */
    @Query("select c.id from CharacterIdEntity c")
    List<Integer> getIds();
}
