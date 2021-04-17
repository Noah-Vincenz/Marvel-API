package com.marvel.api.marvelapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.marvel.api.marvelapi.model.Data;
import com.marvel.api.marvelapi.model.Response;
import com.marvel.api.marvelapi.persistence.entity.CharacterIdEntity;
import com.marvel.api.marvelapi.persistence.repository.CharacterIdRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@Component
class DatabasePopulator implements CommandLineRunner {

    @Value("${marvel.api.baseURL}")
    private String baseURL;
    @Value("${marvel.api.hash}")
    private String hash;
    @Value("${marvel.api.publicKey}")
    private String publicKey;
    private final int LIMIT = 100;
    @Autowired
    private CharacterIdRepository characterIdRepository;

    private static final Logger logger = LoggerFactory.getLogger(DatabasePopulator.class);

    @Override
    public void run(String... args) throws Exception {
        logger.info("Populating the DB with character ids taken from " + baseURL + "...");
        List<CharacterIdEntity> entityList = new ArrayList<>();
        int offset = 0;
        int total = 1;
        while (offset < total) {
            String url = baseURL +
                    "/v1/public/characters?limit=" +
                    LIMIT +
                    "&offset=" +
                    offset +
                    "&ts=1&apikey=" +
                    publicKey +
                    "&hash=" +
                    hash;
            HttpRequest request = HttpRequest.newBuilder(URI.create(url))
                    .header("accept", "application/json")
                    .build();
            HttpResponse<String> stringResponse = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
            Response response = new ObjectMapper().readValue(stringResponse.body(), Response.class);
            Data data = response.data;
            response.data.results
                    .stream().map(c -> c.id)
                    .forEach(id -> entityList.add(new CharacterIdEntity().setId(Integer.parseInt(id))));
            offset += data.count;
            total = data.total;
        }
        characterIdRepository.saveAll(entityList);
        logger.info("Finished populating the DB with " + offset + " character ids.");
    }

}
