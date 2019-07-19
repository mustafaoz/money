package uk.co.incircity.money.service;

import uk.co.incircity.money.ApplicationException;
import uk.co.incircity.money.model.Account;
import uk.co.incircity.money.model.MoneyTransfer;
import uk.co.incircity.money.repository.AccountRepository;
import uk.co.incircity.money.repository.AccountRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class MoneyService {

    @Autowired
    AccountRepository accountRepository;

    public MoneyService() {

    }

    public void transferMoney(MoneyTransfer moneyTransfer) throws ApplicationException {

        Account fromAccount = accountRepository.getAccount(moneyTransfer.getFromAccountNumber());
        Account toAccount = accountRepository.getAccount(moneyTransfer.getToAccountNumber());

        if (!fromAccount.getCurrency().equals(toAccount.getCurrency())) {
            throw new ApplicationException("Currencies NOT match.");
        }

        if (fromAccount.getBalance().compareTo(moneyTransfer.getAmount()) < 0) {
            throw new ApplicationException("Account does NOT enough money.");
        }

        fromAccount.setBalance(fromAccount.getBalance().subtract(moneyTransfer.getAmount()));
        toAccount.setBalance(toAccount.getBalance().add(moneyTransfer.getAmount()));
    }

    public int getAccountCount() {
        return accountRepository.getAccountList().size();
    }

    public List<Account> getAccountList() {
        return accountRepository.getAccountList();
    }

}
