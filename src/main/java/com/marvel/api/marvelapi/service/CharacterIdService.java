package com.marvel.api.marvelapi.service;

import com.marvel.api.marvelapi.persistence.entity.CharacterIdEntity;
import com.marvel.api.marvelapi.persistence.repository.CharacterIdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * This class is used for interacting with the DB repository.
 */
@Component
public class CharacterIdService {

    @Autowired
    private CharacterIdRepository characterIdRepository;

    /**
     * Save a given list of CharacterIdEntities to the DB.
     * @param entityList the list of entities to be saved.
     */
    public void saveEntities(List<CharacterIdEntity> entityList) {
        characterIdRepository.saveAll(entityList);
    }

    /**
     * Get all character IDs stored in the DB.
     * @return a list of all the IDs stored in the DB.
     */
    public List<Integer> getAllIds() {
        return characterIdRepository.getIds();
    }
}
