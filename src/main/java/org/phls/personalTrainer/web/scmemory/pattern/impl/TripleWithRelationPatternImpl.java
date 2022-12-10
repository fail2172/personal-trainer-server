package org.phls.personalTrainer.web.scmemory.pattern.impl;

import org.ostis.scmemory.model.element.edge.EdgeType;
import org.ostis.scmemory.model.element.node.NodeType;
import org.ostis.scmemory.model.element.node.ScNode;
import org.ostis.scmemory.websocketmemory.memory.pattern.DefaultWebsocketScPattern;
import org.ostis.scmemory.websocketmemory.memory.pattern.SearchingPatternTriple;
import org.ostis.scmemory.websocketmemory.memory.pattern.element.AliasPatternElement;
import org.ostis.scmemory.websocketmemory.memory.pattern.element.FixedPatternElement;
import org.ostis.scmemory.websocketmemory.memory.pattern.element.TypePatternElement;
import org.phls.personalTrainer.web.scmemory.pattern.TripleWithRelationPattern;

public class TripleWithRelationPatternImpl implements TripleWithRelationPattern {
    private static final TripleWithRelationPatternImpl HOLDER_INSTANCE = new TripleWithRelationPatternImpl();
    private static final String RELATION_PAIR_ALIAS = "_relation_pair";
    private static final String OTHER_NODE_ALIAS = "_other_node";
    private static final String ACCESS_ARC_ALIAS = "_access_arc";

    public static final int SOURCE_INDEX = 0;
    public static final int TARGET_INDEX = 2;

    private TripleWithRelationPatternImpl() {
    }

    public static TripleWithRelationPatternImpl getInstance(){
        return HOLDER_INSTANCE;
    }

    public DefaultWebsocketScPattern receiveFiveWithSourcePattern(
            ScNode source, EdgeType relationPairEdgeType, NodeType targetType,
            EdgeType accessArcType, ScNode relation) {
        var pattern = new DefaultWebsocketScPattern();
        var relationPairAlias = new AliasPatternElement(RELATION_PAIR_ALIAS);
        var accessArcAlias = new AliasPatternElement(ACCESS_ARC_ALIAS);
        var otherNodeAlias = new AliasPatternElement(OTHER_NODE_ALIAS);

        pattern.addElement(new SearchingPatternTriple(
                new FixedPatternElement(source),
                new TypePatternElement<>(relationPairEdgeType, relationPairAlias),
                new TypePatternElement<>(targetType, otherNodeAlias))
        );
        pattern.addElement(new SearchingPatternTriple(
                new FixedPatternElement(relation),
                new TypePatternElement<>(accessArcType, accessArcAlias),
                relationPairAlias)
        );

        return pattern;
    }

    @Override
    public DefaultWebsocketScPattern receiveFiveWithTargetPattern(
            NodeType sourceType, EdgeType relationPairEdgeType, ScNode target,
            EdgeType accessArcType, ScNode relation) {
        var pattern = new DefaultWebsocketScPattern();
        var relationPairAlias = new AliasPatternElement(RELATION_PAIR_ALIAS);
        var accessArcAlias = new AliasPatternElement(ACCESS_ARC_ALIAS);
        var otherNodeAlias = new AliasPatternElement(OTHER_NODE_ALIAS);

        pattern.addElement(new SearchingPatternTriple(
                new TypePatternElement<>(sourceType, otherNodeAlias),
                new TypePatternElement<>(relationPairEdgeType, relationPairAlias),
                new FixedPatternElement(target))
        );
        pattern.addElement(new SearchingPatternTriple(
                new FixedPatternElement(relation),
                new TypePatternElement<>(accessArcType, accessArcAlias),
                relationPairAlias)
        );

        return pattern;
    }
}
