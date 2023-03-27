package com.prokopovich.bankclient.httpclient.service;

import com.prokopovich.bankclient.httpclient.exception.HttpClientException;
import com.prokopovich.bankclient.httpclient.response.HttpResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import static java.net.HttpURLConnection.HTTP_ACCEPTED;
import static java.net.HttpURLConnection.HTTP_OK;

@Slf4j
public class DefaultHttpClientService implements HttpClientService {

    @Override
    public HttpResponse getData(String url) {

        log.debug("Downloading content by url, {}", url);

        try {
            var connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("GET");

            var response = getResponse(connection, HTTP_OK);
            log.debug(response.toString());

            return response;

        } catch (IOException e) {
            throw new HttpClientException("connection error", e);
        }
    }

    @Override
    public HttpResponse sendData(String url, JSONObject body) {

        log.debug("Send content by url, {}", url);

        try {
            var connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            var writer = new OutputStreamWriter(connection.getOutputStream());
            writer.write(body.toString());
            writer.flush();

            var response = getResponse(connection, HTTP_ACCEPTED);
            log.debug(response.toString());

            return response;

        } catch (IOException e) {
            throw new HttpClientException("connection error", e);
        }
    }

    private HttpResponse getResponse(HttpURLConnection connection, int okStatusCode) throws IOException {

        var errorStream = connection.getResponseCode() == okStatusCode ? null : connection.getErrorStream();

        return HttpResponse.builder()
            .statusCode(connection.getResponseCode())
            .body(IOUtils.toByteArray((errorStream != null ? errorStream : connection.getInputStream())))
            .build();
    }
}
