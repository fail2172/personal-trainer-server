package org.phls.personalTrainer.web.service;

import org.phls.personalTrainer.web.model.ScEntity;

import java.util.List;

public interface ScEntityService<T extends ScEntity> {

    void add(T entity);

    T get(String login);

    List<T> findAll();

    void update(T entity);

    void delete(String login);
}
