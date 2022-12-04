package org.phls.personalTrainer.web.dao;

import org.ostis.scmemory.model.element.node.ScNode;
import org.phls.personalTrainer.web.scmemory.action.ActionNode;
import org.phls.personalTrainer.web.scmemory.action.ActionNodePreparer;
import org.phls.personalTrainer.web.scmemory.action.impl.ActionNodeImpl;
import org.phls.personalTrainer.web.scmemory.action.impl.CreateUserPreparer;
import org.phls.personalTrainer.web.scmemory.action.impl.SearchUserPreparer;
import org.phls.personalTrainer.web.scmemory.agent.AgentRunner;
import org.phls.personalTrainer.web.scmemory.agent.AgentRunnerImpl;
import org.phls.personalTrainer.web.scmemory.exception.ScException;
import org.phls.personalTrainer.web.scmemory.extractor.ScEntityExtractor;
import org.phls.personalTrainer.web.scmemory.extractor.UserExtractor;
import org.phls.personalTrainer.web.scmemory.model.User;
import org.phls.personalTrainer.web.scmemory.node.Nodes;

import java.util.Optional;

public class UserDaoImpl implements UserDao {
    private static final UserDaoImpl HOLDER_INSTANCE = new UserDaoImpl();
    private static final ScEntityExtractor extractor = UserExtractor.getInstance();
    private static final AgentRunner runner = AgentRunnerImpl.getInstance();

    public static UserDaoImpl getInstance() {
        return HOLDER_INSTANCE;
    }

    @Override
    public User create(User user) throws ScException {
        ActionNode actionNode = new ActionNodeImpl(Nodes.ACTION_CREATE_USER.getNode());
        ActionNodePreparer<User> preparer = CreateUserPreparer.getInstance();

        preparer.prepareActionNode(actionNode, user);
        ScNode resultContour = runner.runAgent(actionNode);
        ScNode userNode = extractor.extractScEntity(resultContour);

        return new User(userNode);
    }

    @Override
    public Optional<User> read(String login) throws ScException {
        ActionNode actionNode = new ActionNodeImpl(Nodes.ACTION_SEARCH_USER.getNode());
        ActionNodePreparer<String> preparer = SearchUserPreparer.getInstance();

        preparer.prepareActionNode(actionNode, login);
        ScNode resultContour = runner.runAgent(actionNode);

        try {
            ScNode userNode = extractor.extractScEntity(resultContour);
            return Optional.of(new User(userNode));
        } catch (ScException e) {
            return Optional.empty();
        }
    }
}
