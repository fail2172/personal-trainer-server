package org.phls.personalTrainer.web.scmemory.pattern;

import org.ostis.scmemory.model.element.edge.EdgeType;
import org.ostis.scmemory.model.element.node.NodeType;
import org.ostis.scmemory.model.element.node.ScNode;
import org.ostis.scmemory.websocketmemory.memory.pattern.DefaultWebsocketScPattern;

public interface TriplePattern {
    DefaultWebsocketScPattern receiveTripleWithBeginPattern(ScNode begin, EdgeType edgeType, NodeType targetType);

    DefaultWebsocketScPattern receiveTripleWithTargetPattern(NodeType beginType, EdgeType edgeType, ScNode target);
}
