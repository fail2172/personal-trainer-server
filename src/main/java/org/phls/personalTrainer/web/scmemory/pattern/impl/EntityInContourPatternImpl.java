package org.phls.personalTrainer.web.scmemory.pattern.impl;

import org.ostis.scmemory.model.element.edge.EdgeType;
import org.ostis.scmemory.model.element.node.NodeType;
import org.ostis.scmemory.model.element.node.ScNode;
import org.ostis.scmemory.websocketmemory.memory.pattern.DefaultWebsocketScPattern;
import org.ostis.scmemory.websocketmemory.memory.pattern.SearchingPatternTriple;
import org.ostis.scmemory.websocketmemory.memory.pattern.element.AliasPatternElement;
import org.ostis.scmemory.websocketmemory.memory.pattern.element.FixedPatternElement;
import org.ostis.scmemory.websocketmemory.memory.pattern.element.TypePatternElement;
import org.phls.personalTrainer.web.scmemory.pattern.EntityInContourPattern;

public class EntityInContourPatternImpl implements EntityInContourPattern {
    private static final EntityInContourPatternImpl HOLDER_INSTANCE = new EntityInContourPatternImpl();
    private static final String ENTITY_CLASS_ACCESS_ARC_ALIAS = "_entity_class_access_arc";
    private static final String CONTOUR_ACCESS_ARC_ALIAS = "_contour_access_arc";
    private static final String ENTITY_ALIAS = "_entity";

    public static final int USER_INDEX = 2;

    private EntityInContourPatternImpl() {
    }

    public static EntityInContourPatternImpl getInstance() {
        return HOLDER_INSTANCE;
    }

    @Override
    public DefaultWebsocketScPattern receivePattern(ScNode contour, ScNode entityClass) {
        var pattern = new DefaultWebsocketScPattern();
        var entityAlias = new AliasPatternElement(ENTITY_ALIAS);
        var contourAccessArcAlias = new AliasPatternElement(CONTOUR_ACCESS_ARC_ALIAS);
        var entityClassAccessArcAlias = new AliasPatternElement(ENTITY_CLASS_ACCESS_ARC_ALIAS);

        pattern.addElement(new SearchingPatternTriple(
                new FixedPatternElement(contour),
                new TypePatternElement<>(EdgeType.ACCESS_VAR_POS_PERM, contourAccessArcAlias),
                new TypePatternElement<>(NodeType.VAR, entityAlias))
        );
        pattern.addElement(new SearchingPatternTriple(
                new FixedPatternElement(entityClass),
                new TypePatternElement<>(EdgeType.ACCESS_VAR_POS_PERM, entityClassAccessArcAlias),
                entityAlias)
        );

        return pattern;
    }
}
