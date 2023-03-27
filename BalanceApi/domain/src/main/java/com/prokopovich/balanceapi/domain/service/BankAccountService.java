package com.prokopovich.balanceapi.domain.service;

import com.prokopovich.balanceapi.domain.model.BankAccount;

public interface BankAccountService {

    BankAccount getById(Long id);

    void update(BankAccount account);
}
