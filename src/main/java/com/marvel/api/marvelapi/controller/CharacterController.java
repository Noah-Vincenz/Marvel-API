package com.marvel.api.marvelapi.controller;

import com.marvel.api.marvelapi.persistence.repository.CharacterIdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class CharacterController {

    @Autowired
    private CharacterIdRepository repository;

    /**
     * Get all character ids.
     *
     * @return the list of character ids.
     */
    @GetMapping("/characters")
    public List<Integer> getAllCharacterIds() throws ResourceNotFoundException {
        List<Integer> ids = repository.getAllIds();
        if (ids.isEmpty()) {
            throw new ResourceNotFoundException("Please wait for all the character IDs to be loaded to the database.");
        }
        return repository.getAllIds();
    }
}
