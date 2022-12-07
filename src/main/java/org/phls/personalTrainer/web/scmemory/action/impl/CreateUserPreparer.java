package org.phls.personalTrainer.web.scmemory.action.impl;

import org.ostis.scmemory.model.element.edge.EdgeType;
import org.ostis.scmemory.model.element.link.LinkType;
import org.ostis.scmemory.model.exception.ScMemoryException;
import org.phls.personalTrainer.web.scmemory.action.ActionNode;
import org.phls.personalTrainer.web.scmemory.action.ActionNodePreparer;
import org.phls.personalTrainer.web.scmemory.connection.ScConnection;
import org.phls.personalTrainer.web.scmemory.exception.ScException;
import org.phls.personalTrainer.web.model.impl.User;
import org.phls.personalTrainer.web.scmemory.node.RelationNodes;
import org.phls.personalTrainer.web.scmemory.util.CommonUtils;
import org.phls.personalTrainer.web.scmemory.util.CommonUtilsImpl;

public class CreateUserPreparer implements ActionNodePreparer<User> {
    private static final CreateUserPreparer HOLDER_INSTANCE = new CreateUserPreparer();
    private static final CommonUtils utils = CommonUtilsImpl.getInstance();

    private CreateUserPreparer() {
    }

    public static CreateUserPreparer getInstance() {
        return HOLDER_INSTANCE;
    }

    @Override
    public void prepareActionNode(ActionNode actionNode, User user) throws ScException {
        try (ScConnection connection = new ScConnection()) {
            var loginLink = connection.createStringLink(LinkType.LINK_CONST, user.getLogin());
            var password = connection.createStringLink(LinkType.LINK_CONST, user.getPassword());

            utils.createTripleWithRelation(
                    actionNode.getNode(),
                    EdgeType.ACCESS_CONST_POS_PERM,
                    loginLink,
                    EdgeType.ACCESS_CONST_POS_PERM,
                    RelationNodes.RREL_1.getNode());
            utils.createTripleWithRelation(
                    actionNode.getNode(),
                    EdgeType.ACCESS_CONST_POS_PERM,
                    password,
                    EdgeType.ACCESS_CONST_POS_PERM,
                    RelationNodes.RREL_2.getNode());
        } catch (ScMemoryException e) {
            throw new ScException("ScMemory exception", e);
        } catch (Exception e) {
            throw new ScException("Memory open exception!", e);
        }
    }
}
