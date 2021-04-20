package com.marvel.api.marvelapi.service;

import com.marvel.api.marvelapi.model.ShortCharacter;
import com.marvel.api.marvelapi.persistence.entity.CharacterIdEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
public class MarvelApiServiceTest {

    @InjectMocks
    private MarvelApiService service;
    private final HttpClient httpClient = mock(HttpClient.class);
    private final HttpResponse httpResponse = mock(HttpResponse.class);

    @BeforeEach
    public void setup() {
        service.client = httpClient;
        service.baseURL = "https://www.google.com";
        service.hash = "123";
        service.publicKey = "abc";
    }

    @Test
    public void testGetAllCharacterIds() throws IOException, InterruptedException {
        // Setting count to 3, limit to 3, and total to 12
        // Therefore, there will be 4 loop iterations and 4 requests
        String testResponseWithCharacter = "{\"code\":200,\"status\":\"Ok\",\"copyright\":\"\",\"attributionText\":\"\",\"attributionHTML\":\"\",\"etag\":\"\",\"data\":{\"offset\":0,\"limit\":3,\"total\":10,\"count\":3,\"results\":[{\"id\":1011334,\"name\":\"3-D Man\",\"description\":\"\",\"modified\":\"\",\"thumbnail\":{}},{\"id\":1011335,\"name\":\"Batman\",\"description\":\"\",\"modified\":\"\",\"thumbnail\":{}},{\"id\":1011336,\"name\":\"Superman\",\"description\":\"\",\"modified\":\"\",\"thumbnail\":{}}]}}";

        Mockito
                .when(httpClient.send(any(HttpRequest.class), eq(HttpResponse.BodyHandlers.ofString())))
                .thenReturn(httpResponse);
        Mockito
                .when(httpResponse.body())
                .thenReturn(testResponseWithCharacter);

        List<CharacterIdEntity> entitiesReturned = service.getAllCharacterIds();
        Assertions.assertEquals(12, entitiesReturned.size());
        Mockito.verify(service.client, Mockito.times(4)).send(any(), any());
    }

    @Test
    public void testGetCharacterOfExistingId() throws IOException, InterruptedException {
        String testResponseWithCharacter = "{\"code\":200,\"status\":\"Ok\",\"copyright\":\"\",\"attributionText\":\"\",\"attributionHTML\":\"\",\"etag\":\"\",\"data\":{\"offset\":0,\"limit\":3,\"total\":1,\"count\":1,\"results\":[{\"id\":1011334,\"name\":\"3-D Man\",\"description\":\"\",\"modified\":\"\",\"thumbnail\":{}}]}}";

        Mockito
                .when(httpClient.send(any(HttpRequest.class), eq(HttpResponse.BodyHandlers.ofString())))
                .thenReturn(httpResponse);
        Mockito
                .when(httpResponse.body())
                .thenReturn(testResponseWithCharacter);

        ShortCharacter character = service.getCharacter(1);
        Assertions.assertEquals("3-D Man", character.name);
        Mockito.verify(service.client, Mockito.times(1)).send(any(), any());
    }

    @Test
    public void testGetCharacterOfNonExistingId() throws IOException, InterruptedException {
        // The character id does not exist, ie the data field in the response does not exist
        String testResponseWithoutCharacter = "{\"code\":200,\"status\":\"Ok\",\"copyright\":\"\",\"attributionText\":\"\",\"attributionHTML\":\"\",\"etag\":\"\"}";

        Mockito
                .when(httpClient.send(any(HttpRequest.class), eq(HttpResponse.BodyHandlers.ofString())))
                .thenReturn(httpResponse);
        Mockito
                .when(httpResponse.body())
                .thenReturn(testResponseWithoutCharacter);

        ShortCharacter character = service.getCharacter(0);
        Assertions.assertNull(character);
        Mockito.verify(service.client, Mockito.times(1)).send(any(), any());
    }
}

