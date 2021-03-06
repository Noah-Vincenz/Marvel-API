package com.marvel.api.marvelapi.controller;

import com.marvel.api.marvelapi.model.ShortCharacter;
import com.marvel.api.marvelapi.service.CharacterIdService;
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
    private CharacterIdService characterIdService;

    @Autowired
    private MarvelApiService marvelApiService;

    /**
     * Get all character IDs.
     *
     * @return the JSON array of all the character IDs.
     * @throws ResourceNotFoundException when the IDs have not been fully loaded to the DB.
     */
    @GetMapping("/characters")
    public List<Integer> getAllCharacterIds() {
        List<Integer> ids = characterIdService.getAllIds();
        if (ids.isEmpty()) {
            throw new ResourceNotFoundException("Please wait for all the character IDs to be loaded to the database.");
        }
        return ids;
    }

    /**
     * Get a specific character by ID.
     *
     * @param id the ID of the character to be retrieved - id, name, description, thumbnail.
     * @return the character with the given ID (id, name, description, thumbnail)
     * @throws ResourceNotFoundException when no character could be found that matches the given ID.
     */
    @GetMapping("/characters/{id}")
    public ShortCharacter getCharacterById(@PathVariable("id") Integer id) throws IOException, InterruptedException {
        ShortCharacter shortCharacter = marvelApiService.getCharacter(id);
        if (shortCharacter == null) {
            throw new ResourceNotFoundException("Could not find any character that matches the given id: " + id + ".");
        }
        return shortCharacter;
    }
}
