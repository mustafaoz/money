package uk.co.incircity.money.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.co.incircity.money.ApplicationException;
import uk.co.incircity.money.model.Account;
import uk.co.incircity.money.model.MoneyTransfer;
import uk.co.incircity.money.repository.AccountRepository;
import uk.co.incircity.money.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class MoneyService {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    UserRepository userRepository;

    public boolean transferMoney(MoneyTransfer moneyTransfer) throws ApplicationException {

        if (moneyTransfer.getFromAccountNumber().equals(moneyTransfer.getToAccountNumber())) {
            throw new ApplicationException("Transfer between same accounts is not allowed.");
        }

        Account fromAccount = accountRepository.getAccount(moneyTransfer.getFromAccountNumber());
        Account toAccount = accountRepository.getAccount(moneyTransfer.getToAccountNumber());

        if (fromAccount.getAccountNumber().equals(0)) {
            throw new ApplicationException("From account number could not be found.");
        }

        if (toAccount.getAccountNumber().equals(0)) {
            throw new ApplicationException("To account number could not be found.");
        }

        if (!fromAccount.getCurrency().equals(toAccount.getCurrency())) {
            throw new ApplicationException("Currencies doesn't match between accounts.");
        }

        if (fromAccount.getBalance().compareTo(moneyTransfer.getAmount()) < 0) {
            throw new ApplicationException("From account does not have enough money.");
        }

        // Take away the transfer amount from account
        fromAccount.setBalance(fromAccount.getBalance().subtract(moneyTransfer.getAmount()));

        // Add the transfer amount to account
        toAccount.setBalance(toAccount.getBalance().add(moneyTransfer.getAmount()));

        return true;
    }

    public List<Account> getAccountList() {
        return accountRepository.getAccountList()
                .stream()
                .map(x -> {
                    x.setOwnerName(userRepository.getUser(x.getOwnerId()).getName());
                    return x;
                }).collect(Collectors.toList());
    }

}
