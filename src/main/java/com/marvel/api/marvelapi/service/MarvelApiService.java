package com.marvel.api.marvelapi.service;

import com.fasterxml.jackson.databind.ObjectMapper;
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
import java.net.http.HttpResponse.BodyHandlers;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is used for direct interaction with the Marvel API (see https://developer.marvel.com/docs)
 */
@Component
public class MarvelApiService {

    @Value("${marvel.api.baseURL}")
    public String baseURL;
    @Value("${marvel.api.hash}")
    public String hash;
    @Value("${marvel.api.publicKey}")
    public String publicKey;
    private final int LIMIT = 100; // 100 results is max
    private final int TIMESTAMP = 1;
    public HttpClient client = HttpClient.newHttpClient();
    private final ObjectMapper mapper = new ObjectMapper();

    /**
     * Get all character ids from the API and return a list of created CharacterIdEntities.
     * This method executes multiple GET requests that iterate over all the pages that the Marvel API returns until
     * all the IDs have been collected. For each ID, this method creates a new CharacterIdEntity object and adds this
     * to the list that will ultimately be returned.
     *
     * @return a list of all the CharacterIdEntities that have been created from the retrieved ids.
     * @throws IOException when an IO operation fails or is interrupted during any of the API requests.
     * @throws InterruptedException when the thread is interrupted during any of the API requests.
     */
    public List<CharacterIdEntity> getAllCharacterIds() throws IOException, InterruptedException {
        List<CharacterIdEntity> entityList = new ArrayList<>();
        int offset = 0; // starting with offset 0
        int total = 1; // initially setting this to 1 so we enter the first iteration of the loop
        final String leadingStr = baseURL +
                "/v1/public/characters?limit=" +
                LIMIT +
                "&offset=";
        final String trailingStr = "&ts=" +
                TIMESTAMP +
                "&apikey=" +
                publicKey +
                "&hash=" +
                hash;

        while (offset < total) {
            String uri = leadingStr + offset + trailingStr;
            HttpResponse<String> stringResponse = client.send(buildRequest(uri), BodyHandlers.ofString());
            // use Jackson to map the response body String to our custom Response object
            Response response = mapper.readValue(stringResponse.body(), Response.class);
            Data data = response.data;
            // for each elem in the results list we want to retrieve the ID and add a newly created CharacterIdEntity to our list
            data.results
                    .stream().map(c -> c.id)
                    .forEach(id -> entityList.add(new CharacterIdEntity().setId(id)));
            // increment the offset to jump to our next page
            offset += data.count;
            total = data.total;
        }
        return entityList;
    }

    /**
     * Retrieve the character with a given ID - ID, name, description and thumbnail.
     * @param id The character's ID.
     * @return the retrieved character (ID, name, description and thumbnail) or null if no such character could be found.
     * @throws IOException when an IO operation fails or is interrupted during the API request.
     * @throws InterruptedException when the thread is interrupted during the API request.
     */
    public ShortCharacter getCharacter(Integer id) throws IOException, InterruptedException {
        final String uri = baseURL +
                "/v1/public/characters/" +
                id +
                "?ts=" +
                TIMESTAMP +
                "&apikey=" +
                publicKey +
                "&hash=" +
                hash;

        HttpResponse<String> stringResponse = client.send(buildRequest(uri), BodyHandlers.ofString());
        // use Jackson to map the response body String to our custom Response object
        Response response = mapper.readValue(stringResponse.body(), Response.class);
        // return null and do not throw a NullPointerException
        if (response.data == null) {
            return null;
        }
        return new ShortCharacter(response.data.results.get(0));
    }

    /**
     * Build a request from the given uri.
     * @param uri The URI used for this request.
     * @return The created HttpRequest object.
     */
    private HttpRequest buildRequest(String uri) {
        return HttpRequest.newBuilder(URI.create(uri))
                .header("accept", "application/json")
                .build();
    }
}
