package org.phls.personalTrainer.web.scmemory.util;

import org.ostis.scmemory.model.element.ScElement;
import org.ostis.scmemory.model.element.edge.EdgeType;
import org.ostis.scmemory.model.element.node.ScNode;
import org.phls.personalTrainer.web.scmemory.exception.ScException;

public interface CommonUtils {
    boolean checkEdge(ScNode begin, ScNode end, EdgeType edgeType) throws ScException;

    void createTripleWithRelation(ScElement param1, EdgeType param2, ScElement param3, EdgeType param4, ScElement param5) throws ScException;

    ScNode receiveNode(String systemIdtf) throws Exception;
}
