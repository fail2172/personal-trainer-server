package org.phls.personalTrainer.web.scmemory.agent;

import org.ostis.scmemory.model.element.edge.EdgeType;
import org.ostis.scmemory.model.element.node.NodeType;
import org.ostis.scmemory.model.element.node.ScNode;
import org.ostis.scmemory.model.exception.ScMemoryException;
import org.ostis.scmemory.model.pattern.factory.DefaultScPattern5Factory;
import org.phls.personalTrainer.web.scmemory.action.ActionNode;
import org.phls.personalTrainer.web.scmemory.connection.ScConnection;
import org.phls.personalTrainer.web.scmemory.exception.ScException;
import org.phls.personalTrainer.web.scmemory.exception.TimeoutExceededException;
import org.phls.personalTrainer.web.scmemory.exception.UnsuccessfulDecisionException;
import org.phls.personalTrainer.web.scmemory.node.Nodes;

public class AgentRunnerImpl implements AgentRunner {
    private static final AgentRunnerImpl HOLDER_INSTANCE = new AgentRunnerImpl();
    private static final int PROCESSING_TIME = 5000;

    private AgentRunnerImpl() {
    }

    public static AgentRunnerImpl getInstance() {
        return HOLDER_INSTANCE;
    }

    @Override
    public ScNode runAgent(ActionNode actionNode) throws ScException {
        try (ScConnection connection = new ScConnection()) {
            connection.createEdge(
                    EdgeType.ACCESS_CONST_POS_PERM,
                    Nodes.QUESTION_INITIATED.getNode(),
                    actionNode.getNode());
            if (checkDecision(actionNode)) {
                var pattern = DefaultScPattern5Factory.get(
                        actionNode.getNode(),
                        EdgeType.D_COMMON_VAR,
                        NodeType.VAR_STRUCT,
                        EdgeType.ACCESS_VAR_POS_PERM,
                        Nodes.NREL_ANSWER.getNode()
                );
                var result = connection.find(pattern).findFirst();
                if (result.isPresent())
                    return result.get().get3();
                else
                    throw new ScException("Agent result not found");
            } else
                throw new UnsuccessfulDecisionException("Agent job failed");
        } catch (ScException e) {
            throw e;
        } catch (ScMemoryException e) {
            throw new ScException("ScMemory exception", e);
        } catch (Exception e) {
            throw new ScException("Memory open exception!", e);
        }
    }

    private boolean checkDecision(ActionNode actionNode) throws ScException {
        long startProcessingTime = System.currentTimeMillis();
        while (!actionNode.isSuccessful()) {
            long elapsedTime = System.currentTimeMillis() - startProcessingTime;
            if (elapsedTime < PROCESSING_TIME)
                throw new TimeoutExceededException("Agent timeout exceeded");

            if (actionNode.isUnSuccessful())
                return false;
        }
        return true;
    }
}
