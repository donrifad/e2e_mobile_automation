package com.qa.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.common.ConfigPropertReader;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.MessageFormat;
import java.util.HashMap;

public class HeadSpinUtil {
    public static void installApp() throws URISyntaxException, IOException, InterruptedException {
        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper
                .writerWithDefaultPrettyPrinter()
                .writeValueAsString(new HashMap<>());

        String uri = "https://api-dev.headspin.io/v0/idevice/{0}/installer/install?ipa_id={1}";
        uri = MessageFormat.format(uri, ConfigPropertReader.getProperty("ios.head.spin.device.udid"),
                ConfigPropertReader.getProperty("headspin.appId"));

        HttpRequest request = HttpRequest.newBuilder(new URI(uri))
                .header("Authorization", "Bearer " + ConfigPropertReader.getProperty("headspin.apitoken"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        HttpResponse<String> response = HttpClient.newHttpClient()
                .send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println("Status code of install app: " + response.statusCode());
        System.out.println("response body: " + response.body());
    }

    public static void uninstallApp() throws IOException, URISyntaxException, InterruptedException {
        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper
                .writerWithDefaultPrettyPrinter()
                .writeValueAsString(new HashMap<>());

        String uri = "https://api-dev.headspin.io/v0/idevice/{0}/installer/uninstall?appid={1}";
        uri = MessageFormat.format(uri, ConfigPropertReader.getProperty("ios.head.spin.device.udid"),
                ConfigPropertReader.getProperty("bundleId"));

        HttpRequest request = HttpRequest.newBuilder(new URI(uri))
                .header("Authorization", "Bearer " + ConfigPropertReader.getProperty("headspin.apitoken"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        HttpResponse<String> response = HttpClient.newHttpClient()
                .send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println("Status code of uninstall app: " + response.statusCode());
        System.out.println("response body: " + response.body());
    }
}
