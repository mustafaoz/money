package uk.co.incircity.money.datastore;

import org.springframework.stereotype.Component;
import uk.co.incircity.money.model.Account;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class AccountStoreImpl implements AccountStore {

    private List<Account> accountList = new ArrayList<Account>();

    public AccountStoreImpl() {
        // Initialize a sample data set
        populateInMemoryTestDataStore();
    }

    public List<Account> getAccountList() {
        return accountList;
    }

    private void populateInMemoryTestDataStore() {

        accountList.add(
                new Account.AccountBuilder()
                        .accountName("Test Account")
                        .accountNumber(1)
                        .ownerId(1)
                        .balance(new BigDecimal(10000.00))
                        .currency("GBP")
                        .build());

        accountList.add(
                new Account.AccountBuilder()
                        .accountName("Test Account 2")
                        .accountNumber(2)
                        .ownerId(2)
                        .balance(new BigDecimal(10000.00))
                        .currency("GBP")
                        .build());
    }
}
