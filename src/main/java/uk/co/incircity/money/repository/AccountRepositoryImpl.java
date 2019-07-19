package uk.co.incircity.money.repository;

import org.springframework.beans.factory.annotation.Autowired;
import uk.co.incircity.money.datastore.AccountStore;
import uk.co.incircity.money.datastore.AccountStoreImpl;
import uk.co.incircity.money.model.Account;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AccountRepositoryImpl implements  AccountRepository {

    @Autowired
    AccountStore accountStore;

    @Override
    public List<Account> getAccountList() {
        return accountStore.getAccountList();
    }

    @Override
    public Account getAccount(Integer accountNumber) {
        return accountStore.getAccountList().stream().filter(x -> x.getAccountNumber().equals(accountNumber)).findFirst().get();
    }
}
