package uk.co.incircity.money.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import uk.co.incircity.money.datastore.UserStore;
import uk.co.incircity.money.model.User;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @MockBean
    UserStore userStore;

    @Test
    public void getUser_Success() {
        User user = new User.UserBuilder().id(1).build();
        List userList = Arrays.asList(user);

        when(userStore.getUserList()).thenReturn(userList);

        assertEquals(user, userRepository.getUser(1));
    }
}
