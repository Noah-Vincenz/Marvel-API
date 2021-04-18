package com.marvel.api.marvelapi.persistence.repository;

import com.marvel.api.marvelapi.persistence.entity.CharacterIdEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.util.ArrayList;
import java.util.List;

@DataJpaTest
public class CharacterIdRepositoryTest {
    @Autowired
    CharacterIdRepository repository;

    @Test
    public void testGetIdsWhenRepositoryIsEmpty() {
        List<Integer> idsReturned = repository.getIds();
        Assertions.assertTrue(idsReturned.isEmpty());
    }

    @Test
    public void testGetIdsWhenRepositoryIsPopulated() {
        CharacterIdEntity entity1 = new CharacterIdEntity().setId(1);
        CharacterIdEntity entity2 = new CharacterIdEntity().setId(2);
        CharacterIdEntity entity3 = new CharacterIdEntity().setId(4);

        List<CharacterIdEntity> listOfEntities = new ArrayList<>();
        listOfEntities.add(entity1);
        listOfEntities.add(entity2);
        listOfEntities.add(entity3);

        repository.saveAll(listOfEntities);

        List<Integer> idsReturned = repository.getIds();
        Assertions.assertEquals(idsReturned.size(), 3);
        Assertions.assertTrue(idsReturned.contains(1));
        Assertions.assertTrue(idsReturned.contains(2));
        Assertions.assertTrue(idsReturned.contains(4));
    }
}
