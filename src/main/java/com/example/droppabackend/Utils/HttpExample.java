package com.example.droppabackend.Utils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpExample {
    public static void main(String[] args) {
        try {
            // URL and JSON data
            String url = "https://example.com/api/endpoint";
            String jsonInputString = "{\"name\": \"some name\", \"surname\": \"some surname\"}";

            // Create URL object
            URL obj = new URL(url);

            // Open connection
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            // Set request method
            con.setRequestMethod("POST");

            // Set Bearer token header
            String token = "your_bearer_token_here";
            con.setRequestProperty("Authorization", "Bearer " + token);

            // Set content-type
            con.setRequestProperty("Content-Type", "application/json");

            // Enable output and set JSON data
            con.setDoOutput(true);
            try (DataOutputStream wr = new DataOutputStream(con.getOutputStream())) {
                wr.writeBytes(jsonInputString);
                wr.flush();
            }

            // Get response code
            int responseCode = con.getResponseCode();
            System.out.println("Response Code: " + responseCode);

            // Read response
            try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
                String inputLine;
                StringBuilder response = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                System.out.println("Response: " + response.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}