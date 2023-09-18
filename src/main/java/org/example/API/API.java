package org.example.API;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;

public class API {
    public JSONObject call_API(String id, String routineURL) throws IOException {
        final String API_URL="https://uiuclassroutine.webhunt.workers.dev/?id="+id+"&routine="+routineURL;

        URL url = new URL(API_URL);

        // Open a connection to the URL
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        // Set the request method to GET
        connection.setRequestMethod("GET");

        // Get the response code
        int responseCode = connection.getResponseCode();

        // Check if the request was successful (status code 200)
        if (responseCode == 200) {
            // Create a BufferedReader to read the API response
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            // Read the response line by line
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }

            // Close the BufferedReader
            in.close();

            // Parse the JSON data
            JSONObject json = new JSONObject(response.toString());

            return json;

        }
        System.out.println("Failed to fetch data from the API. Status code: " + responseCode);

        return null;
    }
}
