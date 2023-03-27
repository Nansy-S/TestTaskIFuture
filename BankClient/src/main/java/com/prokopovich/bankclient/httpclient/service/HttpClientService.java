package com.prokopovich.bankclient.httpclient.service;

import com.prokopovich.bankclient.httpclient.response.HttpResponse;
import com.prokopovich.bankclient.httpclient.exception.HttpClientException;
import org.json.simple.JSONObject;

public interface HttpClientService {

    /**
     * @throws HttpClientException if connection error
     */
    HttpResponse getData(String url);

    HttpResponse sendData(String url, JSONObject body);
}
