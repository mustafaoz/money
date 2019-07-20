package uk.co.incircity.money.datastore;

import org.springframework.stereotype.Component;
import uk.co.incircity.money.model.Account;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class AccountStoreInMemoryTestDataImpl implements AccountStore {

    private List<Account> accountList = new ArrayList<Account>();

    public AccountStoreInMemoryTestDataImpl() {
        // Initialize a sample data set
        populateInMemoryTestDataStore();
    }

    public List<Account> getAccountList() {
        return accountList;
    }

    private void populateInMemoryTestDataStore() {

        accountList.add(
                new Account.AccountBuilder()
                        .accountNumber(50001)
                        .ownerId(1001)
                        .balance(new BigDecimal(10000.00))
                        .currency("GBP")
                        .build());

        accountList.add(
                new Account.AccountBuilder()
                        .accountNumber(50002)
                        .ownerId(1002)
                        .balance(new BigDecimal(10000.00))
                        .currency("GBP")
                        .build());

        accountList.add(
                new Account.AccountBuilder()
                        .accountNumber(50003)
                        .ownerId(1003)
                        .balance(new BigDecimal(10000.00))
                        .currency("GBP")
                        .build());

        accountList.add(
                new Account.AccountBuilder()
                        .accountNumber(50004)
                        .ownerId(1004)
                        .balance(new BigDecimal(10000.00))
                        .currency("GBP")
                        .build());

        accountList.add(
                new Account.AccountBuilder()
                        .accountNumber(50005)
                        .ownerId(1005)
                        .balance(new BigDecimal(10000.00))
                        .currency("USD")
                        .build());
    }
}
