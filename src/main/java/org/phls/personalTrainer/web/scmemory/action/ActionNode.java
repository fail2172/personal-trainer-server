package org.phls.personalTrainer.web.scmemory.action;

import org.ostis.scmemory.model.element.node.ScNode;
import org.phls.personalTrainer.web.scmemory.exception.ScException;

public interface ActionNode {
    boolean isSuccessful() throws ScException;

    boolean isUnSuccessful() throws ScException;

    ScNode getNode();
}
