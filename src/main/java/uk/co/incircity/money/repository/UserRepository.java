package uk.co.incircity.money.repository;

import uk.co.incircity.money.model.User;

public interface UserRepository {

    User getUser(Integer id);

}
