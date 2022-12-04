package org.phls.personalTrainer.web.scmemory.action;

import org.phls.personalTrainer.web.scmemory.exception.ScException;

public interface ActionNodePreparer<T> {
    void prepareActionNode(ActionNode actionNode, T entity) throws ScException;
}
