package com.prokopovich.bankclient.domain.service;

import com.prokopovich.bankclient.domain.exception.ClientServiceException;
import com.prokopovich.bankclient.domain.model.ClientSettings;
import com.prokopovich.bankclient.httpclient.exception.HttpClientException;
import com.prokopovich.bankclient.httpclient.service.HttpClientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import static com.prokopovich.bankclient.BankClientApp.DEFAULT_URL;

@Slf4j
@RequiredArgsConstructor
public class DefaultClientService implements ClientService {

    private final HttpClientService httpClient;

    @Override
    public void sendRequest(ClientSettings settings) {

        while (true) {
            double readProbability = (double)settings.getReadQuota() /
                (double)(settings.getReadQuota() + settings.getWriteQuota());

            if (ThreadLocalRandom.current().nextDouble() < readProbability) {
                getBalance(randomFromList(settings.getReadIdList()));
            } else {
                changeBalance(randomFromList(settings.getWriteIdList()), 1L);
            }
        }
    }

    private void getBalance(Long id) {

        try {
            var response = httpClient.getData(DEFAULT_URL + "/account/" + id);

            if (response.getStatusCode() == 200) {
                log.info("Current account balance: {}. Account id: {}.", response.bodyToString(), id);
            } else {
                log.error("Unable to get balance for account with id: {}. {}", id, response.bodyToString());
            }

        } catch (HttpClientException e) {
            throw new ClientServiceException("Unable to get balance for account with id: " + id, e);
        }
    }

    private void changeBalance(Long id, Long amount) {

        var requestJson = new JSONObject();
        requestJson.put("amount", amount);

        try {
            var response = httpClient.sendData(DEFAULT_URL + "/account/" + id, requestJson);

            log.info("StatusCode: {}. {}.", response.getStatusCode(), response.bodyToString());

        } catch (HttpClientException e) {
            throw new ClientServiceException("Unable to change balance for account with id: " + id, e);
        }
    }

    private Long randomFromList(List<Long> list) {

        return list.get(new Random().nextInt(list.size()));
    }
}
