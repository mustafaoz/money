package uk.co.incircity.money.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.assertEquals;

import static org.mockito.Mockito.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import uk.co.incircity.money.ApplicationException;
import uk.co.incircity.money.model.Account;
import uk.co.incircity.money.model.MoneyTransfer;
import uk.co.incircity.money.repository.AccountRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MoneyServiceTest {

    @Autowired
    MoneyService moneyService;

    @MockBean
    private AccountRepository accountRepository;

    @Before
    public void init() {

        List<Account> accountList = new ArrayList<Account>();

        Account account = new Account.AccountBuilder().accountName("Test Account").accountNumber(1).ownerId(1).balance(new BigDecimal(100.99)).build();

        accountList.add(account);

        when(accountRepository.getAccountList()).thenReturn(accountList);

    }

    @Test
    public void isCountOfAccounts() {
        assertEquals (1, moneyService.getAccountCount());
    }

    @Test(expected = ApplicationException.class)
    public void transferMoney_DifferentCurrencies_ApplicationException() throws ApplicationException {

        Account gbpAccount = new Account.AccountBuilder()
                .accountNumber(1)
                .ownerId(1)
                .balance(new BigDecimal(100.00))
                .currency("GBP")
                .build();

        Account usdAccount = new Account.AccountBuilder()
                .accountNumber(2)
                .ownerId(2)
                .balance(new BigDecimal(200.00))
                .currency("USD")
                .build();

        MoneyTransfer moneyTransfer = new MoneyTransfer.MoneyTransferBuilder()
                .fromAccountNumber(1)
                .toAccountNumber(2)
                .amount(new BigDecimal(10.00))
                .build();

        when(accountRepository.getAccount(1)).thenReturn(gbpAccount);
        when(accountRepository.getAccount(2)).thenReturn(usdAccount);

        moneyService.transferMoney(moneyTransfer);
    }

    @Test(expected = ApplicationException.class)
    public void transferMoney_FromAccountNotMoneyEnough_ApplicationException() throws ApplicationException {

        Account fromAccount = new Account.AccountBuilder()
                .accountNumber(1)
                .ownerId(1)
                .balance(new BigDecimal(1.00))
                .currency("GBP")
                .build();

        Account toAccount = new Account.AccountBuilder()
                .accountNumber(2)
                .ownerId(2)
                .balance(new BigDecimal(200.00))
                .currency("GBP")
                .build();

        MoneyTransfer moneyTransfer = new MoneyTransfer.MoneyTransferBuilder()
                .fromAccountNumber(1)
                .toAccountNumber(2)
                .amount(new BigDecimal(10.00))
                .build();

        when(accountRepository.getAccount(1)).thenReturn(fromAccount);
        when(accountRepository.getAccount(2)).thenReturn(toAccount);

        moneyService.transferMoney(moneyTransfer);
    }

    @Test
    public void transferMoney_Success() throws ApplicationException {

        Account fromAccount = new Account.AccountBuilder()
                .accountNumber(1)
                .ownerId(1)
                .balance(new BigDecimal(100.00))
                .currency("GBP")
                .build();

        Account toAccount = new Account.AccountBuilder()
                .accountNumber(2)
                .ownerId(2)
                .balance(new BigDecimal(200.00))
                .currency("GBP")
                .build();

        MoneyTransfer moneyTransfer = new MoneyTransfer.MoneyTransferBuilder()
                .fromAccountNumber(1)
                .toAccountNumber(2)
                .amount(new BigDecimal(10.00))
                .build();

        when(accountRepository.getAccount(1)).thenReturn(fromAccount);
        when(accountRepository.getAccount(2)).thenReturn(toAccount);

        moneyService.transferMoney(moneyTransfer);

        Assert.assertEquals(new BigDecimal(90), fromAccount.getBalance());
        Assert.assertEquals(new BigDecimal(210), toAccount.getBalance());
    }

}
