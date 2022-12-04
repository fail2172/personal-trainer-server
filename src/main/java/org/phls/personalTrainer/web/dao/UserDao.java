package org.phls.personalTrainer.web.dao;

import org.phls.personalTrainer.web.scmemory.exception.ScException;
import org.phls.personalTrainer.web.scmemory.model.User;

import java.util.Optional;

public interface UserDao {
    User create(User user) throws ScException;

    Optional<User> read(String login) throws ScException;
}
