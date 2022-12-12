package org.phls.personalTrainer.web.dao.impl;

import org.phls.personalTrainer.web.dao.ScEntityDao;
import org.phls.personalTrainer.web.model.impl.Diet;
import org.phls.personalTrainer.web.scmemory.agent.AgentRunner;
import org.phls.personalTrainer.web.scmemory.agent.AgentRunnerImpl;
import org.phls.personalTrainer.web.scmemory.exception.ScException;
import org.phls.personalTrainer.web.scmemory.extractor.EntityFromContourExtractor;
import org.phls.personalTrainer.web.scmemory.extractor.ScEntityExtractor;
import org.phls.personalTrainer.web.scmemory.extractor.impl.DietExtractor;
import org.phls.personalTrainer.web.scmemory.extractor.impl.EntityFromContourExtractorImpl;
import org.phls.personalTrainer.web.scmemory.node.DefaultDiet;

import java.util.List;
import java.util.Optional;

public class DietDao implements ScEntityDao<Diet> {
    private static final DietDao HOLDER_INSTANCE = new DietDao();
    private static final EntityFromContourExtractor contourExtractor = EntityFromContourExtractorImpl.getInstance();
    private static final ScEntityExtractor<Diet> dietExtractor = DietExtractor.getInstance();
    private static final AgentRunner runner = AgentRunnerImpl.getInstance();

    private DietDao() {
    }

    public static DietDao getInstance() {
        return HOLDER_INSTANCE;
    }
    @Override
    public Diet create(Diet entity) throws ScException {
        return null;
    }

    @Override
    public Optional<Diet> read(String login) throws ScException {
        return Optional.empty();
    }

    public Diet readDefaultDiet(DefaultDiet dietType) throws ScException {
        return dietExtractor.extractEntity(dietType.getNode());
    }

    @Override
    public List<Diet> readAll() throws ScException {
        return null;
    }

    @Override
    public Diet update(Diet entity) throws ScException {
        return null;
    }

    @Override
    public void delete(String login) throws ScException {

    }
}
