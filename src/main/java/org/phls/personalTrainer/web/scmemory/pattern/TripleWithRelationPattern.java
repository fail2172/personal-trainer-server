package org.phls.personalTrainer.web.scmemory.pattern;

import org.ostis.scmemory.model.element.ScElement;
import org.ostis.scmemory.model.element.edge.EdgeType;
import org.ostis.scmemory.model.element.node.NodeType;
import org.ostis.scmemory.model.element.node.ScNode;
import org.ostis.scmemory.websocketmemory.memory.pattern.DefaultWebsocketScPattern;

public interface TripleWithRelationPattern {
    DefaultWebsocketScPattern receiveFiveWithSourcePattern(
            ScNode source, EdgeType relationPairEdgeType, NodeType targetType, EdgeType accessArcType, ScNode relation);

    DefaultWebsocketScPattern receiveFiveWithTargetPattern(
            NodeType sourceType, EdgeType relationPairEdgeType, ScNode target, EdgeType accessArcType, ScNode relation);
}
