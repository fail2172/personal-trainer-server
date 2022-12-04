package org.phls.personalTrainer.web.scmemory.agent;

import org.ostis.scmemory.model.element.node.ScNode;
import org.phls.personalTrainer.web.scmemory.action.ActionNode;
import org.phls.personalTrainer.web.scmemory.exception.ScException;
import org.phls.personalTrainer.web.scmemory.exception.UnsuccessfulDecisionException;

public interface AgentRunner {
    ScNode runAgent(ActionNode actionNode) throws ScException, UnsuccessfulDecisionException;
}
