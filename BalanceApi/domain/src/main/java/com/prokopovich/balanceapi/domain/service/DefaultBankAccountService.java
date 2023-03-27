package com.prokopovich.balanceapi.domain.service;

import com.prokopovich.balanceapi.domain.dao.BankAccountRepository;
import com.prokopovich.balanceapi.domain.exception.BankAccountServiceException;
import com.prokopovich.balanceapi.domain.model.BankAccount;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DefaultBankAccountService implements BankAccountService {

    private final BankAccountRepository bankAccountRepository;

    @Override
    @Cacheable("balanceData")
    public BankAccount getById(Long id) {

        var account = bankAccountRepository.findById(id);
        if (account.isEmpty())
            throw new BankAccountServiceException("Error: account with id = " + id + " not found", 500);

        return account.get();
    }

    @Override
    @CachePut("balanceData")
    public void update(BankAccount account) {

        bankAccountRepository.save(account);
    }
}
