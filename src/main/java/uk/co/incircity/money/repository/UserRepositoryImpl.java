package uk.co.incircity.money.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import uk.co.incircity.money.datastore.AccountStore;
import uk.co.incircity.money.datastore.UserStore;
import uk.co.incircity.money.model.Account;
import uk.co.incircity.money.model.User;

import java.util.List;

@Repository
public class UserRepositoryImpl implements  UserRepository {

    @Autowired
    UserStore userStore;

    @Override
    public User getUser(Integer userId) {
        return userStore.getUserList()
                .stream()
                .filter(x -> x.getId().equals(userId))
                .findFirst()
                .orElseGet(() -> new User.UserBuilder().id(0).build());
    }
}
