package org.phls.personalTrainer.web.scmemory.util;

import org.ostis.scmemory.model.element.ScElement;
import org.ostis.scmemory.model.element.edge.EdgeType;
import org.ostis.scmemory.model.element.node.NodeType;
import org.ostis.scmemory.model.element.node.ScNode;
import org.phls.personalTrainer.web.scmemory.exception.ScException;

import java.util.List;
import java.util.Optional;

public interface CommonUtils {
    boolean checkEdge(ScNode begin, ScNode end, EdgeType edgeType) throws ScException;

    void createTripleWithRelation(ScElement param1, EdgeType param2, ScElement param3, EdgeType param4, ScElement param5) throws ScException;

    ScNode receiveNode(String systemIdtf) throws Exception;

    Optional<ScNode> receiveTripleBegin(NodeType beginType, EdgeType edgeType, ScNode target) throws ScException;

    Optional<ScNode> receiveTripleTarget(ScNode begin, EdgeType edgeType, NodeType targetType) throws ScException;

    List<? extends ScElement> receiveAllFromSet(ScNode set, NodeType elementsType) throws ScException;
}
