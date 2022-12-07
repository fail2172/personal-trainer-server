package org.phls.personalTrainer.web.scmemory.pattern.impl;

import org.ostis.scmemory.model.element.edge.EdgeType;
import org.ostis.scmemory.model.element.node.NodeType;
import org.ostis.scmemory.model.element.node.ScNode;
import org.ostis.scmemory.websocketmemory.memory.pattern.DefaultWebsocketScPattern;
import org.ostis.scmemory.websocketmemory.memory.pattern.SearchingPatternTriple;
import org.ostis.scmemory.websocketmemory.memory.pattern.element.AliasPatternElement;
import org.ostis.scmemory.websocketmemory.memory.pattern.element.FixedPatternElement;
import org.ostis.scmemory.websocketmemory.memory.pattern.element.TypePatternElement;
import org.phls.personalTrainer.web.scmemory.pattern.TriplePattern;

public class TriplePatternImpl implements TriplePattern {
    private static final TriplePatternImpl HOLDER_INSTANCE = new TriplePatternImpl();
    private static final String EDGE_ALIAS = "_edge";
    private static final String OTHER_NODE_ALIAS = "_other_node";

    public static int BEGIN_INDEX = 0;
    public static int TARGET_INDEX = 2;

    private TriplePatternImpl() {
    }

    public static TriplePatternImpl getInstance(){
        return HOLDER_INSTANCE;
    }

    @Override
    public DefaultWebsocketScPattern receiveTripleWithBeginPattern(ScNode begin, EdgeType edgeType, NodeType targetType) {
        return receiveTriplePattern(begin, edgeType, targetType, true);
    }

    @Override
    public DefaultWebsocketScPattern receiveTripleWithTargetPattern(NodeType beginType, EdgeType edgeType, ScNode target) {
        return receiveTriplePattern(target, edgeType, beginType, false);
    }

    private DefaultWebsocketScPattern receiveTriplePattern(ScNode node, EdgeType edgeType, NodeType otherNodeType, boolean isBegin) {
        var pattern = new DefaultWebsocketScPattern();
        var edgeAlias = new AliasPatternElement(EDGE_ALIAS);
        var otherNode = new AliasPatternElement(OTHER_NODE_ALIAS);

        if (isBegin) {
            pattern.addElement(new SearchingPatternTriple(
                    new FixedPatternElement(node),
                    new TypePatternElement<>(edgeType, edgeAlias),
                    new TypePatternElement<>(otherNodeType, otherNode))
            );
        } else {
            pattern.addElement(new SearchingPatternTriple(
                    new TypePatternElement<>(otherNodeType, otherNode),
                    new TypePatternElement<>(edgeType, edgeAlias),
                    new FixedPatternElement(node))
            );
        }
        return pattern;
    }
}
