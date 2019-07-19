package uk.co.incircity.money.repository;

import uk.co.incircity.money.model.Account;
import java.util.List;

public interface AccountRepository {

    List<Account> getAccountList();

    Account getAccount(Integer accountNumber);

}
