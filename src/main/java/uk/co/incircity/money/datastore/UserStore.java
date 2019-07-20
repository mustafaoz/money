package uk.co.incircity.money.datastore;

import uk.co.incircity.money.model.User;

import java.util.List;

public interface UserStore {
    List<User> getUserList();

}
