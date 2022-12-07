package org.phls.personalTrainer.web.scmemory.pattern.impl;

import org.ostis.scmemory.model.element.edge.EdgeType;
import org.ostis.scmemory.model.element.node.NodeType;
import org.ostis.scmemory.model.element.node.ScNode;
import org.ostis.scmemory.websocketmemory.memory.pattern.DefaultWebsocketScPattern;
import org.ostis.scmemory.websocketmemory.memory.pattern.SearchingPatternTriple;
import org.ostis.scmemory.websocketmemory.memory.pattern.element.AliasPatternElement;
import org.ostis.scmemory.websocketmemory.memory.pattern.element.FixedPatternElement;
import org.ostis.scmemory.websocketmemory.memory.pattern.element.TypePatternElement;
import org.phls.personalTrainer.web.scmemory.pattern.SetPattern;

public class SetPatternImpl implements SetPattern {
    private static final SetPatternImpl HOLDER_INSTANCE = new SetPatternImpl();
    private static final String EDGE_ALIAS = "_edge";
    private static final String SET_ELEMENT_ALIAS = "_set_element";

    public static int SET_ELEMENT_INDEX = 2;

    private SetPatternImpl() {
    }

    public static SetPatternImpl getInstance() {
        return HOLDER_INSTANCE;
    }

    @Override
    public DefaultWebsocketScPattern receiveSetPattern(ScNode set, NodeType elementsType) {
        var pattern = new DefaultWebsocketScPattern();
        var edgeAlias = new AliasPatternElement(EDGE_ALIAS);
        var setElement = new AliasPatternElement(SET_ELEMENT_ALIAS);

        pattern.addElement(new SearchingPatternTriple(
                new FixedPatternElement(set),
                new TypePatternElement<>(EdgeType.ACCESS_VAR_POS_PERM, edgeAlias),
                new TypePatternElement<>(elementsType, setElement))
        );

        return pattern;
    }
}
