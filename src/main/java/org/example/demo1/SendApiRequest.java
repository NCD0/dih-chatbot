package org.example.demo1;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class SendApiRequest {
    private final String apiLink = "http://localhost:3000"; // server.js express api
    private final HttpClient client = HttpClient.newHttpClient();
    private final ObjectMapper objectMapper = new ObjectMapper();

    public HttpResponse<String> sendApiRequest(Body body) throws IOException, InterruptedException {
        System.out.println(body);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(apiLink + "/prompt"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(objectMapper.writeValueAsString(body)))
                .build();

        return client.send(request, HttpResponse.BodyHandlers.ofString());
    }
}
