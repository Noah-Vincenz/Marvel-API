package com.marvel.api.marvelapi.service;

import com.marvel.api.marvelapi.persistence.entity.CharacterIdEntity;
import com.marvel.api.marvelapi.persistence.repository.CharacterIdRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class CharacterIdServiceTest {

    @Mock
    private CharacterIdRepository characterIdRepository;

    @InjectMocks
    private CharacterIdService service;

    @Test
    public void testSaveEntities() {
        List<CharacterIdEntity> list = new ArrayList<>();

        service.saveEntities(list);

        Mockito.verify(characterIdRepository, Mockito.times(1)).saveAll(list);
    }

    @Test
    public void testGetAllIds() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(3);

        Mockito
                .when(characterIdRepository.getIds())
                .thenReturn(list);

        Assertions.assertEquals(list, service.getAllIds(), "The IDs returned");
        Mockito.verify(characterIdRepository, Mockito.times(1)).getIds();
    }
}
