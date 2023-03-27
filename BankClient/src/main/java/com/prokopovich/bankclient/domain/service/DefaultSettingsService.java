package com.prokopovich.bankclient.domain.service;

import com.prokopovich.bankclient.domain.model.ClientSettings;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Random;

import static com.prokopovich.bankclient.BankClientApp.*;

@Slf4j
@RequiredArgsConstructor
public class DefaultSettingsService implements SettingsService {

    @Override
    public ClientSettings setRandomSettings() {

        var random = new Random();

        var readIdList = random.longs(1L, READ_ID_LIST_MAX_VALUE).limit(READ_ID_LIST_LIMIT).boxed().toList();
        var writeIdList = random.longs(1L, WRITE_ID_LIST_MAX_VALUE).limit(WRITE_ID_LIST_LIMIT).boxed().toList();

        return ClientSettings.builder()
            .threadCount(random.nextInt(THREAD_COUNT_BOUND) + 1)
            .readQuota(random.nextInt(READ_QUOTA_BOUND))
            .writeQuota(random.nextInt(WRITE_QUOTA_BOUND))
            .readIdList(readIdList)
            .writeIdList(writeIdList)
            .build();
    }
}
