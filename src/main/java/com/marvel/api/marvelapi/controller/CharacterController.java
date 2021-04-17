package com.marvel.api.marvelapi.controller;

import com.marvel.api.marvelapi.model.ShortCharacter;
import com.marvel.api.marvelapi.persistence.repository.CharacterIdRepository;
import com.marvel.api.marvelapi.service.MarvelApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class CharacterController {

    @Autowired
    private CharacterIdRepository repository;

    @Autowired
    private MarvelApiService marvelApiService;

    /**
     * Get all character ids.
     *
     * @return the list of character ids.
     */
    @GetMapping("/characters")
    public List<Integer> getAllCharacterIds() {
        List<Integer> ids = repository.getAllIds();
        if (ids.isEmpty()) {
            throw new ResourceNotFoundException("Please wait for all the character IDs to be loaded to the database.");
        }
        return repository.getAllIds();
    }

    /**
     * Get a specific character by id.
     *
     * @return the character with the given id.
     */
    @GetMapping("/characters/{id}")
    public ShortCharacter getCharacter(@PathVariable("id") Integer id) throws IOException, InterruptedException {
        ShortCharacter shortCharacter = marvelApiService.getCharacter(id);
        if (shortCharacter == null) {
            throw new ResourceNotFoundException("Could not find any character that matches the given id: " + id + ".");
        }
        return shortCharacter;
    }
}
