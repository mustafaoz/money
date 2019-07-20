package uk.co.incircity.money.datastore;

import org.springframework.stereotype.Component;
import uk.co.incircity.money.model.Account;
import uk.co.incircity.money.model.User;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserStoreInMemoryTestDataImpl implements UserStore {

    private List<User> userList = new ArrayList<User>();

    public UserStoreInMemoryTestDataImpl() {
        // Initialize a sample data set
        populateInMemoryTestDataStore();
    }

    public List<User> getUserList() {
        return userList;
    }

    private void populateInMemoryTestDataStore() {
        userList.add(new User.UserBuilder().id(1001).name("David Lane").build());
        userList.add(new User.UserBuilder().id(1002).name("Luis Marques").build());
        userList.add(new User.UserBuilder().id(1003).name("Elman Hasanov").build());
        userList.add(new User.UserBuilder().id(1004).name("Paul Marsh").build());
        userList.add(new User.UserBuilder().id(1005).name("Shanon Shmire").build());
    }
}
