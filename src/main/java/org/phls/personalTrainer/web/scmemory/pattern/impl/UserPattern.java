package org.phls.personalTrainer.web.scmemory.pattern.impl;

import org.ostis.scmemory.model.element.edge.EdgeType;
import org.ostis.scmemory.model.element.link.LinkType;
import org.ostis.scmemory.model.element.node.ScNode;
import org.ostis.scmemory.websocketmemory.memory.pattern.DefaultWebsocketScPattern;
import org.ostis.scmemory.websocketmemory.memory.pattern.SearchingPatternTriple;
import org.ostis.scmemory.websocketmemory.memory.pattern.element.AliasPatternElement;
import org.ostis.scmemory.websocketmemory.memory.pattern.element.FixedPatternElement;
import org.ostis.scmemory.websocketmemory.memory.pattern.element.TypePatternElement;
import org.phls.personalTrainer.web.scmemory.node.RelationNodes;
import org.phls.personalTrainer.web.scmemory.pattern.EntityPattern;

public class UserPattern implements EntityPattern {
    private static final UserPattern HOLDER_INSTANCE = new UserPattern();
    private static final String LOGIN_RELATION_PAIR_ALIAS = "_login_relation_pair";
    private static final String LOGIN_ACCESS_ARC_ALIAS = "_login_access_arc";
    private static final String LOGIN_ALIAS = "_login";
    private static final String PASSWORD_RELATION_PAIR_ALIAS = "_password_relation_pair";
    private static final String PASSWORD_ACCESS_ARC_ALIAS = "_password_access_arc";
    private static final String PASSWORD_ALIAS = "_password";

    public static final int LOGIN_INDEX = 2;
    public static final int PASSWORD_INDEX = 5;

    private UserPattern() {
    }

    public static UserPattern getInstance() {
        return HOLDER_INSTANCE;
    }

    @Override
    public DefaultWebsocketScPattern receivePattern(ScNode userNode) {
        var pattern = new DefaultWebsocketScPattern();
        var loginRelationPairAlias = new AliasPatternElement(LOGIN_RELATION_PAIR_ALIAS);
        var loginAccessArcAlias = new AliasPatternElement(LOGIN_ACCESS_ARC_ALIAS);
        var loginAlias = new AliasPatternElement(LOGIN_ALIAS);
        var passwordRelationPairAlias = new AliasPatternElement(PASSWORD_RELATION_PAIR_ALIAS);
        var passwordAccessArcAlias = new AliasPatternElement(PASSWORD_ACCESS_ARC_ALIAS);
        var passwordAlias = new AliasPatternElement(PASSWORD_ALIAS);

        pattern.addElement(new SearchingPatternTriple(
                new FixedPatternElement(userNode),
                new TypePatternElement<>(EdgeType.D_COMMON_VAR, loginRelationPairAlias),
                new TypePatternElement<>(LinkType.LINK_VAR, loginAlias))
        );
        pattern.addElement(new SearchingPatternTriple(
                new FixedPatternElement(userNode),
                new TypePatternElement<>(EdgeType.D_COMMON_VAR, passwordRelationPairAlias),
                new TypePatternElement<>(LinkType.LINK_VAR, passwordAlias))
        );
        pattern.addElement(new SearchingPatternTriple(
                new FixedPatternElement(RelationNodes.NREL_LOGIN.getNode()),
                new TypePatternElement<>(EdgeType.ACCESS_VAR_POS_PERM, loginAccessArcAlias),
                loginRelationPairAlias)
        );
        pattern.addElement(new SearchingPatternTriple(
                new FixedPatternElement(RelationNodes.NREL_PASSWORD.getNode()),
                new TypePatternElement<>(EdgeType.ACCESS_VAR_POS_PERM, passwordAccessArcAlias),
                passwordRelationPairAlias));
        return pattern;
    }
}
