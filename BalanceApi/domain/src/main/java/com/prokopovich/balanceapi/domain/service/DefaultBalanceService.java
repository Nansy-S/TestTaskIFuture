package com.prokopovich.balanceapi.domain.service;

import com.prokopovich.balanceapi.domain.util.LockById;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class DefaultBalanceService implements BalanceService {

    private final BankAccountService accountService;

    @Override
    public Long getBalance(Long id) {

        log.debug("Getting balance by id {}: ", id);

        return accountService.getById(id).getBalance();
    }

    @Override
    public void changeBalance(Long id, Long amount) {

        log.debug("Updating balance by id {}: ", id);
        LockById lockById = new LockById();

        try {
            lockById.lock(id);

            var account = accountService.getById(id);

            account.setBalance(account.getBalance() + amount);
            accountService.update(account);

        } finally {
            lockById.unlock(id);
        }
    }
}
