package com.marvel.api.marvelapi.service;

import com.marvel.api.marvelapi.persistence.entity.CharacterIdEntity;
import com.marvel.api.marvelapi.persistence.repository.CharacterIdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CharacterIdService {

    @Autowired
    private CharacterIdRepository characterIdRepository;

    public void saveEntities(List<CharacterIdEntity> entityList) {
        characterIdRepository.saveAll(entityList);
    }

    public List<Integer> getAllIds() {
        return characterIdRepository.getIds();
    }
}
