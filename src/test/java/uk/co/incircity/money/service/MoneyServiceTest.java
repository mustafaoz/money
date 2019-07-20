package uk.co.incircity.money.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import uk.co.incircity.money.ApplicationException;
import uk.co.incircity.money.model.Account;
import uk.co.incircity.money.model.MoneyTransfer;
import uk.co.incircity.money.repository.AccountRepository;

import java.math.BigDecimal;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MoneyServiceTest {

    @Autowired
    MoneyService moneyService;

    @MockBean
    private AccountRepository accountRepository;

    @Before
    public void init() {

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

    @Test(expected = ApplicationException.class)
    public void transferMoney_FromAccountDoesNotExist_ApplicationException() throws ApplicationException {

        Account fromAccount = new Account.AccountBuilder()
                .accountNumber(0)
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

    @Test(expected = ApplicationException.class)
    public void transferMoney_ToAccountDoesNotExist_ApplicationException() throws ApplicationException {

        Account fromAccount = new Account.AccountBuilder()
                .accountNumber(1)
                .ownerId(1)
                .balance(new BigDecimal(1.00))
                .currency("GBP")
                .build();

        Account toAccount = new Account.AccountBuilder()
                .accountNumber(0)
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

    @Test(expected = ApplicationException.class)
    public void transferMoney_BetweenSameAccounts_ApplicationException() throws ApplicationException {

        MoneyTransfer moneyTransfer = new MoneyTransfer.MoneyTransferBuilder()
                .fromAccountNumber(1)
                .toAccountNumber(1)
                .amount(new BigDecimal(10.00))
                .build();

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
