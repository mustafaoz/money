package uk.co.incircity.money.datastore;

import uk.co.incircity.money.model.Account;

import java.util.List;

public interface AccountStore {

    List<Account> getAccountList();
}
