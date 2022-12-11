package org.phls.personalTrainer.web.dao.impl;

import org.ostis.scmemory.model.element.node.ScNode;
import org.phls.personalTrainer.web.dao.ScEntityDao;
import org.phls.personalTrainer.web.model.impl.User;
import org.phls.personalTrainer.web.scmemory.action.ActionNode;
import org.phls.personalTrainer.web.scmemory.action.ActionNodePreparer;
import org.phls.personalTrainer.web.scmemory.action.impl.ActionNodeImpl;
import org.phls.personalTrainer.web.scmemory.action.impl.CreateUserPreparer;
import org.phls.personalTrainer.web.scmemory.action.impl.SearchUserPreparer;
import org.phls.personalTrainer.web.scmemory.agent.AgentRunner;
import org.phls.personalTrainer.web.scmemory.agent.AgentRunnerImpl;
import org.phls.personalTrainer.web.scmemory.exception.ScException;
import org.phls.personalTrainer.web.scmemory.extractor.EntityFromContourExtractor;
import org.phls.personalTrainer.web.scmemory.extractor.impl.UserFromContourExtractor;
import org.phls.personalTrainer.web.scmemory.node.ActionNodes;

import java.util.List;
import java.util.Optional;

public class UserDaoImpl implements ScEntityDao<User> {
    private static final UserDaoImpl HOLDER_INSTANCE = new UserDaoImpl();
    private static final EntityFromContourExtractor<User> extractor = UserFromContourExtractor.getInstance();
    private static final AgentRunner runner = AgentRunnerImpl.getInstance();

    public static UserDaoImpl getInstance() {
        return HOLDER_INSTANCE;
    }

    @Override
    public void create(User user) throws ScException {
        ActionNode actionNode = new ActionNodeImpl(ActionNodes.ACTION_CREATE_USER.getNode());
        ActionNodePreparer<User> preparer = CreateUserPreparer.getInstance();

        preparer.prepareActionNode(actionNode, user);
        ScNode resultContour = runner.runAgent(actionNode);
    }

    @Override
    public Optional<User> read(String login) throws ScException {
        ActionNode actionNode = new ActionNodeImpl(ActionNodes.ACTION_SEARCH_USER.getNode());
        ActionNodePreparer<String> preparer = SearchUserPreparer.getInstance();

        preparer.prepareActionNode(actionNode, login);
        ScNode resultContour = runner.runAgent(actionNode);

        try {
            return Optional.of(extractor.extractScEntity(resultContour));
        } catch (ScException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<User> readAll() throws ScException {
        return null;
    }

    @Override
    public void update(User user) throws ScException {

    }

    @Override
    public void delete(String login) throws ScException {

    }
}
