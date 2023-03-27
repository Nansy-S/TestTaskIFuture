package com.prokopovich.bankclient;

import com.prokopovich.bankclient.domain.exception.ClientServiceException;
import com.prokopovich.bankclient.domain.service.ClientService;
import com.prokopovich.bankclient.domain.service.DefaultClientService;
import com.prokopovich.bankclient.domain.service.DefaultSettingsService;
import com.prokopovich.bankclient.domain.service.SettingsService;
import com.prokopovich.bankclient.httpclient.service.DefaultHttpClientService;
import com.prokopovich.bankclient.httpclient.service.HttpClientService;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class BankClientApp {

    public static final String DEFAULT_URL = "http://localhost:8080/api";

    public static final int THREAD_COUNT_BOUND = 20;
    public static final int READ_QUOTA_BOUND = 100;
    public static final int WRITE_QUOTA_BOUND = 100;
    public static final int READ_ID_LIST_LIMIT = 20;
    public static final int WRITE_ID_LIST_LIMIT = 20;
    public static final Long READ_ID_LIST_MAX_VALUE = 50L;
    public static final Long WRITE_ID_LIST_MAX_VALUE = 50L;

    public static void main(String[] args) {

        final HttpClientService httpClient = new DefaultHttpClientService();
        final SettingsService settingsService = new DefaultSettingsService();
        final ClientService clientService = new DefaultClientService(httpClient);

        log.info("Application is started");

        try {

            var settings = settingsService.setRandomSettings();
            clientService.sendRequest(settings);

            ExecutorService executor = Executors.newFixedThreadPool(settings.getThreadCount());

            for(int i = 0; i < settings.getThreadCount(); i++) {
                executor.submit(() -> clientService.sendRequest(settings));
            }

            executor.shutdown();

        } catch (ClientServiceException e) {
            log.error(e.getMessage());
        }
    }
}
