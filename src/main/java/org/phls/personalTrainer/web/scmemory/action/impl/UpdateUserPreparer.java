package org.phls.personalTrainer.web.scmemory.action.impl;

import org.ostis.scmemory.model.element.edge.EdgeType;
import org.ostis.scmemory.model.element.link.LinkType;
import org.ostis.scmemory.model.element.node.NodeType;
import org.ostis.scmemory.model.element.node.ScNode;
import org.ostis.scmemory.model.exception.ScMemoryException;
import org.phls.personalTrainer.web.model.impl.User;
import org.phls.personalTrainer.web.scmemory.action.ActionNode;
import org.phls.personalTrainer.web.scmemory.action.ActionNodePreparer;
import org.phls.personalTrainer.web.scmemory.connection.ScConnection;
import org.phls.personalTrainer.web.scmemory.exception.ScException;
import org.phls.personalTrainer.web.scmemory.node.DiseaseNodes;
import org.phls.personalTrainer.web.scmemory.node.RelationNodes;
import org.phls.personalTrainer.web.scmemory.util.CommonUtils;
import org.phls.personalTrainer.web.scmemory.util.CommonUtilsImpl;

import java.util.List;

public class UpdateUserPreparer implements ActionNodePreparer<User> {
    private static final UpdateUserPreparer HOLDER_INSTANCE = new UpdateUserPreparer();
    private static final CommonUtils utils = CommonUtilsImpl.getInstance();

    private UpdateUserPreparer() {
    }

    public static UpdateUserPreparer getInstance() {
        return HOLDER_INSTANCE;
    }

    @Override
    public void prepareActionNode(ActionNode actionNode, User user) throws ScException {
        try (ScConnection connection = new ScConnection()) {
            var loginLink = connection.createStringLink(LinkType.LINK_CONST, user.getLogin());
            final ScNode userParamSet = connection.createNode(NodeType.CONST_TUPLE);

            utils.createTripleWithRelation(
                    actionNode.getNode(),
                    EdgeType.ACCESS_CONST_POS_PERM,
                    loginLink,
                    EdgeType.ACCESS_CONST_POS_PERM,
                    RelationNodes.RREL_1.getNode());

            utils.createTripleWithRelation(
                    actionNode.getNode(),
                    EdgeType.ACCESS_CONST_POS_PERM,
                    userParamSet,
                    EdgeType.ACCESS_CONST_POS_PERM,
                    RelationNodes.RREL_2.getNode());

            connection.createEdge(
                    EdgeType.ACCESS_CONST_POS_PERM,
                    RelationNodes.NREL_USER_PARAMS.getNode(),
                    userParamSet);

            if (!user.getDiseases().isEmpty())
                addDiseasesToUserParams(connection, userParamSet, user.getDiseases());

        } catch (ScMemoryException e) {
            throw new ScException("ScMemory exception", e);
        } catch (Exception e) {
            throw new ScException("Memory open exception!", e);
        }
    }

    private void addDiseasesToUserParams(ScConnection connection, ScNode userParamSet, List<DiseaseNodes> diseases)
            throws ScMemoryException, ScException {
        final ScNode diseaseSet = connection.createNode(NodeType.CONST_TUPLE);

        utils.createTripleWithRelation(
                userParamSet,
                EdgeType.D_COMMON_CONST,
                diseaseSet,
                EdgeType.ACCESS_CONST_POS_PERM,
                RelationNodes.NREL_DISEASES_PARAM.getNode());

        for (DiseaseNodes disease : diseases)
            connection.createEdge(EdgeType.ACCESS_CONST_POS_PERM, diseaseSet, disease.getNode());
    }
}
