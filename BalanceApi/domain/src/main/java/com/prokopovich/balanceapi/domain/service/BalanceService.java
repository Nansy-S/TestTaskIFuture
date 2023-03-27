package com.prokopovich.balanceapi.domain.service;

public interface BalanceService {

    Long getBalance(Long id);

    void changeBalance(Long id, Long amount);
}
