package org.phls.personalTrainer.web.dao.impl;

import org.ostis.scmemory.model.element.node.ScNode;
import org.phls.personalTrainer.web.dao.ScEntityDao;
import org.phls.personalTrainer.web.model.impl.User;
import org.phls.personalTrainer.web.scmemory.action.ActionNode;
import org.phls.personalTrainer.web.scmemory.action.ActionNodePreparer;
import org.phls.personalTrainer.web.scmemory.action.impl.ActionNodeImpl;
import org.phls.personalTrainer.web.scmemory.action.impl.CreateUserPreparer;
import org.phls.personalTrainer.web.scmemory.action.impl.SearchUserPreparer;
import org.phls.personalTrainer.web.scmemory.action.impl.UpdateUserPreparer;
import org.phls.personalTrainer.web.scmemory.agent.AgentRunner;
import org.phls.personalTrainer.web.scmemory.agent.AgentRunnerImpl;
import org.phls.personalTrainer.web.scmemory.exception.ScException;
import org.phls.personalTrainer.web.scmemory.extractor.EntityFromContourExtractor;
import org.phls.personalTrainer.web.scmemory.extractor.ScEntityExtractor;
import org.phls.personalTrainer.web.scmemory.extractor.impl.EntityFromContourExtractorImpl;
import org.phls.personalTrainer.web.scmemory.extractor.impl.UserExtractor;
import org.phls.personalTrainer.web.scmemory.node.ActionNodes;
import org.phls.personalTrainer.web.scmemory.node.Nodes;

import java.util.List;
import java.util.Optional;

public class UserDao implements ScEntityDao<User> {
    private static final UserDao HOLDER_INSTANCE = new UserDao();
    private static final EntityFromContourExtractor contourExtractor = EntityFromContourExtractorImpl.getInstance();
    private static final ScEntityExtractor<User> userExtractor = UserExtractor.getInstance();
    private static final AgentRunner runner = AgentRunnerImpl.getInstance();

    private UserDao() {
    }

    public static UserDao getInstance() {
        return HOLDER_INSTANCE;
    }

    @Override
    public User create(User user) throws ScException {
        ActionNode actionNode = new ActionNodeImpl(ActionNodes.ACTION_CREATE_USER.getNode());
        ActionNodePreparer<User> preparer = CreateUserPreparer.getInstance();

        preparer.prepareActionNode(actionNode, user);
        ScNode resultContour = runner.runAgent(actionNode);
        ScNode userNode = contourExtractor.extractScEntity(resultContour, Nodes.CONCEPT_USER.getNode());

        return userExtractor.extractEntity(userNode);
    }

    @Override
    public Optional<User> read(String login) throws ScException {
        ActionNode actionNode = new ActionNodeImpl(ActionNodes.ACTION_SEARCH_USER.getNode());
        ActionNodePreparer<String> preparer = SearchUserPreparer.getInstance();

        preparer.prepareActionNode(actionNode, login);
        ScNode resultContour = runner.runAgent(actionNode);

        try {
            ScNode userNode = contourExtractor.extractScEntity(resultContour, Nodes.CONCEPT_USER.getNode());
            return Optional.of(userExtractor.extractEntity(userNode));
        } catch (ScException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<User> readAll() throws ScException {
        return null;
    }

    @Override
    public User update(User user) throws ScException {
        ActionNode actionNode = new ActionNodeImpl(ActionNodes.ACTION_UPDATE_USER.getNode());
        ActionNodePreparer<User> preparer = UpdateUserPreparer.getInstance();

        preparer.prepareActionNode(actionNode, user);
        ScNode resultContour = runner.runAgent(actionNode);
        ScNode userNode = contourExtractor.extractScEntity(resultContour, Nodes.CONCEPT_USER.getNode());

        return userExtractor.extractEntity(userNode);
    }

    @Override
    public void delete(String login) throws ScException {

    }
}
