package com.currencyconvert;

import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class CurrencyConverter {

    private static final String POSTS_API_URL = "https://v6.exchangerate-api.com/v6/669c7b1411f17c1c203a0c18/latest/USD";

    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println(convert(200, "GBP", "KRW"));
    }

    public static String getJsonString() throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .header("accept", "application/json")
                .uri(URI.create(POSTS_API_URL))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }

    public static double convert(double amt, String origin, String destination) throws IOException, InterruptedException {
        JsonNode node = JSON.parse(getJsonString());
        double originRate = node.get("conversion_rates").get(origin).asDouble();
        double convertedToUsd = amt / originRate;
        return convertedToUsd * node.get("conversion_rates").get(destination).asDouble();
    }
}
