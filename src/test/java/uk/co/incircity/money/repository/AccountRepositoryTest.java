package uk.co.incircity.money.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import uk.co.incircity.money.datastore.AccountStore;
import uk.co.incircity.money.model.Account;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountRepositoryTest {

    @Autowired
    AccountRepository accountRepository;

    @MockBean
    AccountStore accountStore;

    @Test
    public void getAccount_Success() {

        Account account = new Account.AccountBuilder()
                .accountNumber(1)
                .build();

        List accountList = new ArrayList();
        accountList.add(account);

        when(accountStore.getAccountList()).thenReturn(accountList);

        assertEquals(account, accountRepository.getAccount(1));
    }
}
