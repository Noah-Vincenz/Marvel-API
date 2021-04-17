package com.marvel.api.marvelapi.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.marvel.api.marvelapi.model.Character;
import com.marvel.api.marvelapi.model.Data;
import com.marvel.api.marvelapi.model.Response;
import com.marvel.api.marvelapi.model.ShortCharacter;
import com.marvel.api.marvelapi.persistence.entity.CharacterIdEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@Component
public class MarvelApiService {

    @Value("${marvel.api.baseURL}")
    private String baseURL;
    @Value("${marvel.api.hash}")
    private String hash;
    @Value("${marvel.api.publicKey}")
    private String publicKey;
    private final int LIMIT = 100;
    private HttpClient client;
    private ObjectMapper mapper;

    public MarvelApiService() {
        client = HttpClient.newHttpClient();
        mapper = new ObjectMapper();
    }

    public List<CharacterIdEntity> getAllCharacterIds() throws IOException, InterruptedException {
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
            HttpResponse<String> stringResponse = client.send(request, HttpResponse.BodyHandlers.ofString());
            Response response = mapper.readValue(stringResponse.body(), Response.class);
            Data data = response.data;
            response.data.results
                    .stream().map(c -> c.id)
                    .forEach(id -> entityList.add(new CharacterIdEntity().setId(id)));
            offset += data.count;
            total = data.total;
        }
        return entityList;
    }

    public ShortCharacter getCharacter(Integer id) throws IOException, InterruptedException {
        String url = baseURL +
                "/v1/public/characters/" +
                id +
                "?ts=1&apikey=" +
                publicKey +
                "&hash=" +
                hash;
        HttpRequest request = HttpRequest.newBuilder(URI.create(url))
                .header("accept", "application/json")
                .build();
        HttpResponse<String> stringResponse = client.send(request, HttpResponse.BodyHandlers.ofString());
        Response response = mapper.readValue(stringResponse.body(), Response.class);
        if (response.data == null) {
            return null;
        }
        Character character = response.data.results.get(0);
        return new ShortCharacter(character);
    }
}
