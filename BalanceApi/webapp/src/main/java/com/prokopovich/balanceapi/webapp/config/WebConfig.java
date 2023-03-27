package com.prokopovich.balanceapi.webapp.config;

import com.prokopovich.balanceapi.domain.dao.BankAccountRepository;
import com.prokopovich.balanceapi.domain.service.BalanceService;
import com.prokopovich.balanceapi.domain.service.BankAccountService;
import com.prokopovich.balanceapi.domain.service.DefaultBalanceService;
import com.prokopovich.balanceapi.domain.service.DefaultBankAccountService;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories("com.prokopovich.balanceapi.domain.dao")
@EntityScan("com.prokopovich.balanceapi.domain.model")
public class WebConfig {

    @Bean
    public BankAccountService bankAccountService(BankAccountRepository bankAccountRepository) {

        return new DefaultBankAccountService(bankAccountRepository);
    }

    @Bean
    public BalanceService balanceService(BankAccountService accountService) {

        return new DefaultBalanceService(accountService);
    }
}
