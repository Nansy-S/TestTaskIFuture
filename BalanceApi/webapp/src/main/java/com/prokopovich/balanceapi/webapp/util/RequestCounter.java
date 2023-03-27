package com.prokopovich.balanceapi.webapp.util;

import lombok.extern.slf4j.Slf4j;

import java.util.Timer;
import java.util.TimerTask;

@Slf4j
public class RequestCounter {

    public static Long getBalanceCounter = 0L;
    public static Long changeBalanceCounter = 0L;

    public static void countRequest(int timePeriod) {

        new Timer().scheduleAtFixedRate(new TimerTask() {
            public void run() {

                log.info(
                    "Number of getBalance requests: {}. Number of changeBalance requests: {}. " +
                        "Amount of requests: {}. Time period: {}",
                    getBalanceCounter,
                    changeBalanceCounter,
                    getBalanceCounter + changeBalanceCounter,
                    timePeriod);

                getBalanceCounter = 0L;
                changeBalanceCounter = 0L;

            }
        }, 0, timePeriod);
    }
}
