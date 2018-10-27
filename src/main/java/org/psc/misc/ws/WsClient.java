package org.psc.misc.ws;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WsClient {
    private static final Logger LOGGER = LoggerFactory.getLogger(WsClient.class);

    public static void main(String[] args) throws IOException {
        WsClient.callWorker();
    }

    public static void callWorker() throws IOException {
        URL url = new URL("http://localhost:8080/workerapp/worker");

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.setRequestMethod("GET");
        connection.connect();
        int responseCode = connection.getResponseCode();
        String response = WsClient.read(connection.getInputStream());
        connection.disconnect();
        LOGGER.info("HTTP {} - {}", String.valueOf(responseCode), response);
    }

    private static String read(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
        String line;
        StringBuffer content = new StringBuffer();
        while ((line = bufferedReader.readLine()) != null) {
            content.append(line);
        }
        bufferedReader.close();
        return content.toString();
    }
}
