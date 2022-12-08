package org.phls.personalTrainer.web.dao;

import org.phls.personalTrainer.web.model.ScEntity;
import org.phls.personalTrainer.web.scmemory.exception.ScException;

import java.util.List;
import java.util.Optional;

public interface ScEntityDao <T extends ScEntity> {

    void create(T entity) throws ScException;

    Optional<T> read(String login) throws ScException;

    List<T> readAll() throws ScException;

    void update(T entity) throws ScException;

    void delete(String login) throws ScException;

}
