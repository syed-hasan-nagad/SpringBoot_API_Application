package com.tech.api_using_springboot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class GetRequests {


    public String getUsers(String id) {

        HttpURLConnection connection;
        BufferedReader reader;
        String line;
        StringBuilder responseContent = new StringBuilder();
        try {
            //Constructing URL with query params appended
            String queryParam = "id=" + id;
            URI uri = new URI("http", "jsonplaceholder.typicode.com", "/users", queryParam, null);
            URL url = uri.toURL();
            // setting up the connection
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);

            int status = connection.getResponseCode();
            if (status > 299) {
                reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
            } else {
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            }
            while ((line = reader.readLine()) != null) {
                responseContent.append(line);
            }
            reader.close();
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
        return responseContent.toString();
    }
}
