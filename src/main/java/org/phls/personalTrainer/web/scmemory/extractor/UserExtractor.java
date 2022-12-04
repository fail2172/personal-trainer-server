package org.phls.personalTrainer.web.scmemory.extractor;

import org.ostis.scmemory.model.element.ScElement;
import org.ostis.scmemory.model.element.edge.EdgeType;
import org.ostis.scmemory.model.element.node.NodeType;
import org.ostis.scmemory.model.element.node.ScNode;
import org.ostis.scmemory.model.exception.ScMemoryException;
import org.ostis.scmemory.websocketmemory.memory.pattern.DefaultWebsocketScPattern;
import org.ostis.scmemory.websocketmemory.memory.pattern.SearchingPatternTriple;
import org.ostis.scmemory.websocketmemory.memory.pattern.element.AliasPatternElement;
import org.ostis.scmemory.websocketmemory.memory.pattern.element.FixedPatternElement;
import org.ostis.scmemory.websocketmemory.memory.pattern.element.TypePatternElement;
import org.phls.personalTrainer.web.scmemory.connection.ScConnection;
import org.phls.personalTrainer.web.scmemory.exception.ScException;
import org.phls.personalTrainer.web.scmemory.node.Nodes;

public class UserExtractor implements ScEntityExtractor {
    private static final UserExtractor HOLDER_INSTANCE = new UserExtractor();
    private static final String USER_ALIAS = "_user_alias";
    private static final String CONTOUR_ACCESS_ARC_ALIAS = "_contour_access_arc_alias";
    private static final String USER_ACCESS_ARC_ALIAS = "_user_access_arc_alias";
    private static final int USER_INDEX = 2;

    private UserExtractor() {
    }

    public static UserExtractor getInstance() {
        return HOLDER_INSTANCE;
    }

    @Override
    public ScNode extractScEntity(ScNode contour) throws ScException {
        var pattern = new DefaultWebsocketScPattern();
        var userAlias = new AliasPatternElement(USER_ALIAS);
        var contourAccessArcAlias = new AliasPatternElement(CONTOUR_ACCESS_ARC_ALIAS);
        var userAccessArcAlias = new AliasPatternElement(USER_ACCESS_ARC_ALIAS);

        pattern.addElement(new SearchingPatternTriple(
                new FixedPatternElement(contour),
                new TypePatternElement<>(EdgeType.ACCESS_VAR_POS_PERM, contourAccessArcAlias),
                new TypePatternElement<>(NodeType.VAR, userAlias))
        );
        pattern.addElement(new SearchingPatternTriple(
                new FixedPatternElement(Nodes.CONCEPT_USER.getNode()),
                new TypePatternElement<>(EdgeType.ACCESS_VAR_POS_PERM, userAccessArcAlias),
                userAlias)
        );
        try (ScConnection connection = new ScConnection()) {
            var userConstruction = connection.find(pattern).findFirst();
            if (userConstruction.isPresent()) {
                ScElement userNode = userConstruction.get().toList().get(USER_INDEX);
                return (ScNode) userNode;
            } else
                throw new ScException("User not found");
        } catch (ScException e) {
            throw e;
        } catch (ScMemoryException e) {
            throw new ScException("ScMemory exception", e);
        } catch (Exception e) {
            throw new ScException("Memory open exception!", e);
        }
    }
}
