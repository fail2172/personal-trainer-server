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

public class AddUserDiseases implements ActionNodePreparer<User> {
    private static final AddUserDiseases HOLDER_INSTANCE = new AddUserDiseases();
    private static final CommonUtils utils = CommonUtilsImpl.getInstance();

    private AddUserDiseases() {
    }

    public static AddUserDiseases getInstance() {
        return HOLDER_INSTANCE;
    }

    @Override
    public void prepareActionNode(ActionNode actionNode, User user) throws ScException {
        try (ScConnection connection = new ScConnection()) {
            var loginLink = connection.createStringLink(LinkType.LINK_CONST, user.getLogin());
            final ScNode userParamSet = receiveUserParamSet(actionNode, connection);
            final ScNode diseasesSet = connection.createNode(NodeType.CONST_TUPLE);

            utils.createTripleWithRelation(
                    actionNode.getNode(),
                    EdgeType.ACCESS_CONST_POS_PERM,
                    loginLink,
                    EdgeType.ACCESS_CONST_POS_PERM,
                    RelationNodes.RREL_1.getNode());
            utils.createTripleWithRelation(
                    userParamSet,
                    EdgeType.D_COMMON_CONST,
                    diseasesSet,
                    EdgeType.ACCESS_CONST_POS_PERM,
                    RelationNodes.NREL_DISEASES_PARAM.getNode());

            List<DiseaseNodes> userDiseases = user.getDiseases();
            for (DiseaseNodes diseases : userDiseases)
                connection.createEdge(EdgeType.ACCESS_CONST_POS_PERM, diseasesSet, diseases.getNode());

        } catch (ScMemoryException e) {
            throw new ScException("ScMemory exception", e);
        } catch (Exception e) {
            throw new ScException("Memory open exception!", e);
        }
    }

    private ScNode receiveUserParamSet(ActionNode actionNode, ScConnection connection)
            throws ScMemoryException, ScException {
        ScNode userParamSet;

        var userParamSetOptional = utils.receiveTripleTarget(
                actionNode.getNode(),
                EdgeType.ACCESS_CONST_POS_PERM,
                NodeType.CONST_TUPLE
        );

        if (userParamSetOptional.isEmpty()) {
            userParamSet = connection.createNode(NodeType.CONST_TUPLE);
            utils.createTripleWithRelation(
                    actionNode.getNode(),
                    EdgeType.ACCESS_CONST_POS_PERM,
                    userParamSet,
                    EdgeType.ACCESS_CONST_POS_PERM,
                    RelationNodes.RREL_2.getNode()
            );
            connection.createEdge(
                    EdgeType.ACCESS_CONST_POS_PERM,
                    RelationNodes.NREL_USER_PARAMS.getNode(),
                    userParamSet
            );
        } else
            userParamSet = userParamSetOptional.get();

        return userParamSet;
    }
}
