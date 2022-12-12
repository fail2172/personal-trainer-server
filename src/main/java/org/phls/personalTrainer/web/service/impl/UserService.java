package org.phls.personalTrainer.web.service.impl;

import org.phls.personalTrainer.web.dao.ScEntityDao;
import org.phls.personalTrainer.web.dao.impl.UserDao;
import org.phls.personalTrainer.web.model.impl.User;
import org.phls.personalTrainer.web.scmemory.exception.ScException;
import org.phls.personalTrainer.web.service.ScEntityService;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserService implements ScEntityService<User> {

    private static UserService instance;

    private final ScEntityDao<User> userDao = UserDao.getInstance();

    private final Logger logger = Logger.getLogger(UserService.class.getName());

    public static synchronized UserService getInstance() {
        if (instance == null) {
            instance = new UserService();
        }
        return instance;
    }

    @Override
    public void add(User entity) {
        try {
            userDao.create(entity);
        } catch (ScException e) {
            logger.log(Level.SEVERE, String.join(" : ", e.getMessage(), e.getCause().toString()));
        }
    }

    @Override
    public User get(String login) {
        Optional<User> user = Optional.empty();
        try {
            user = userDao.read(login);
        } catch (ScException e) {
            logger.log(Level.SEVERE, String.join(" : ", e.getMessage(), e.getCause().toString()));
        }
        return user.orElseThrow();
    }

    @Override
    public List<User> findAll() {
        try {
            return userDao.readAll();
        } catch (ScException e) {
            logger.log(Level.SEVERE, String.join(" : ", e.getMessage(), e.getCause().toString()));
        }
        return Collections.emptyList();
    }

    @Override
    public void update(User entity) {
        try {
            userDao.update(entity);
        } catch (ScException e) {
            logger.log(Level.SEVERE, String.join(" : ", e.getMessage(), e.getCause().toString()));
        }
    }

    @Override
    public void delete(String login) {
        try {
            userDao.delete(login);
        } catch (ScException e) {
            logger.log(Level.SEVERE, String.join(" : ", e.getMessage(), e.getCause().toString()));
        }
    }
}
