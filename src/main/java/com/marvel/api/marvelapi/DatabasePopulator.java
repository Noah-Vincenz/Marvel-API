package com.marvel.api.marvelapi;

import com.marvel.api.marvelapi.persistence.entity.CharacterIdEntity;
import com.marvel.api.marvelapi.service.CharacterIdService;
import com.marvel.api.marvelapi.service.MarvelApiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
class DatabasePopulator implements CommandLineRunner {
    @Value("${marvel.api.baseURL}")
    private String baseURL;
    @Autowired
    private MarvelApiService marvelApiService;
    @Autowired
    private CharacterIdService characterIdService;

    private static final Logger logger = LoggerFactory.getLogger(DatabasePopulator.class);

    @Override
    public void run(String... args) throws Exception {
        logger.info("Populating the DB with character ids taken from " + baseURL + "...");
        List<CharacterIdEntity> entityList = marvelApiService.getAllCharacterIds();
        characterIdService.saveEntities(entityList);
        logger.info("Finished populating the DB with " + entityList.size() + " character ids.");
    }

}
