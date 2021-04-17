package com.marvel.api.marvelapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.marvel.api.marvelapi.model.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Component
class DatabasePopulator implements CommandLineRunner {

    @Value("${marvel.api.baseURL}")
    private String baseURL;
    @Value("${marvel.api.hash}")
    private String hash;
    @Value("${marvel.api.publicKey}")
    private String publicKey;

    private static final Logger logger = LoggerFactory.getLogger(DatabasePopulator.class);

    @Override
    public void run(String... args) throws Exception {
        logger.info("Populating DB with character ids taken from " + baseURL);
        HttpClient client = HttpClient.newHttpClient();
        String url = baseURL +
                "/v1/public/characters?limit=100&offset=1&ts=1&apikey=" +
                publicKey +
                "&hash=" +
                hash;
        HttpRequest request = HttpRequest.newBuilder(URI.create(url))
                .header("accept", "application/json")
                .build();
        HttpResponse<String> stringResponse = client.send(request, HttpResponse.BodyHandlers.ofString());
        Response response = new ObjectMapper().readValue(stringResponse.body(), Response.class);
        System.out.println(response.data.results.get(0).id);
    }

}
