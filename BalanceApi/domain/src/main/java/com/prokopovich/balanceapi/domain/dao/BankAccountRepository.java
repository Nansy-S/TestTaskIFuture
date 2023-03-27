package com.prokopovich.balanceapi.domain.dao;

import com.prokopovich.balanceapi.domain.model.BankAccount;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankAccountRepository extends CrudRepository<BankAccount, Long> {
}
