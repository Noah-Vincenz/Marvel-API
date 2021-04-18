package com.marvel.api.marvelapi.model;

/**
 * This POJO class represents a Response object from the Marvel API (see https://developer.marvel.com/docs).
 * When sending a request to the API we can expect a response of this structure.
 */
public class Response {
    public int code;
    public String status;
    public String copyright;
    public String attributionText;
    public String attributionHTML;
    public String etag;
    public Data data;
}
