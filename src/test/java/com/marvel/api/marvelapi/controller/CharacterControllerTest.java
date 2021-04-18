package com.marvel.api.marvelapi.controller;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.util.AssertionErrors.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.anyInt;
import com.marvel.api.marvelapi.model.ShortCharacter;
import com.marvel.api.marvelapi.service.CharacterIdService;
import com.marvel.api.marvelapi.service.MarvelApiService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class CharacterControllerTest {

    @Mock
    private CharacterIdService characterIdService;

    @Mock
    private MarvelApiService marvelApiService;

    @InjectMocks
    private CharacterController characterController;

    @Test
    public void testGetAllCharacterIdsWhenCharactersExist() {
        List<Integer> ids = new ArrayList<>();
        ids.add(1);
        ids.add(2);
        ids.add(4);

        Mockito
                .when(characterIdService.getAllIds())
                .thenReturn(ids);

        assertEquals("The returned list of ids", ids, characterController.getAllCharacterIds());
        Mockito.verify(characterIdService, Mockito.times(1)).getAllIds();
    }

    @Test
    public void testGetAllCharacterIdsWhenNoCharactersExist() {
        Mockito
                .when(characterIdService.getAllIds())
                .thenReturn(new ArrayList<>());

        Exception exception = assertThrows(ResourceNotFoundException.class, () -> characterController.getAllCharacterIds());

        String expectedMessage = "Please wait for all the character IDs to be loaded to the database.";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
        Mockito.verify(characterIdService, Mockito.times(1)).getAllIds();
    }

    @Test
    public void testGetCharacterByIdWhenCharacterExists() throws Exception {
        ShortCharacter mockedCharacter = mock(ShortCharacter.class);
        Mockito
                .when(marvelApiService.getCharacter(anyInt()))
                .thenReturn(mockedCharacter);

        assertEquals("The returned ShortCharacter object", mockedCharacter, characterController.getCharacterById(1));
        Mockito.verify(marvelApiService, Mockito.times(1)).getCharacter(anyInt());
    }

    @Test
    public void testGetCharacterByIdWhenCharacterDoesNotExists() throws Exception {
        int id = 1;
        Mockito
                .when(marvelApiService.getCharacter(anyInt()))
                .thenReturn(null);

        Exception exception = assertThrows(ResourceNotFoundException.class, () -> characterController.getCharacterById(id));

        String expectedMessage = "Could not find any character that matches the given id: " + id + ".";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
        Mockito.verify(marvelApiService, Mockito.times(1)).getCharacter(anyInt());
    }

}
