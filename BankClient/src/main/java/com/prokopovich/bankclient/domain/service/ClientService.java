package com.prokopovich.bankclient.domain.service;

import com.prokopovich.bankclient.domain.model.ClientSettings;

public interface ClientService {

    void sendRequest(ClientSettings settings);
}
