package org.phls.personalTrainer.web.scmemory.action.impl;

import org.ostis.scmemory.model.element.edge.EdgeType;
import org.ostis.scmemory.model.element.node.NodeType;
import org.ostis.scmemory.model.element.node.ScNode;
import org.ostis.scmemory.model.exception.ScMemoryException;
import org.phls.personalTrainer.web.scmemory.action.ActionNode;
import org.phls.personalTrainer.web.scmemory.connection.ScConnection;
import org.phls.personalTrainer.web.scmemory.exception.ScException;
import org.phls.personalTrainer.web.scmemory.util.CommonUtils;
import org.phls.personalTrainer.web.scmemory.util.CommonUtilsImpl;
import org.phls.personalTrainer.web.scmemory.node.Nodes;

public class ActionNodeImpl implements ActionNode {
    private static final CommonUtils utils = CommonUtilsImpl.getInstance();
    private final ScNode node;

    public ActionNodeImpl(ScNode actionNode) throws ScException {
        try (ScConnection connection = new ScConnection()) {
            node = connection.createNode(NodeType.NODE);
            connection.createEdge(EdgeType.ACCESS_CONST_POS_PERM, actionNode, node);
        } catch (ScException e) {
            throw e;
        } catch (ScMemoryException e) {
            throw new ScException("ScMemory exception", e);
        } catch (Exception e) {
            throw new ScException("Memory open exception!", e);
        }
    }

    @Override
    public boolean isSuccessful() throws ScException {
        return utils.checkEdge(Nodes.QUESTION_FINISHED_SUCCESSFULLY.getNode(), node, EdgeType.ACCESS_CONST_POS_PERM);
    }

    @Override
    public boolean isUnSuccessful() throws ScException {
        return utils.checkEdge(Nodes.QUESTION_FINISHED_UNSUCCESSFULLY.getNode(), node, EdgeType.ACCESS_CONST_POS_PERM);
    }

    @Override
    public ScNode getNode() {
        return node;
    }
}
