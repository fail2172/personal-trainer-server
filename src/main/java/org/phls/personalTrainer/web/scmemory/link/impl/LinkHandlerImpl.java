package org.phls.personalTrainer.web.scmemory.link.impl;

import org.ostis.scmemory.model.element.edge.EdgeType;
import org.ostis.scmemory.model.element.link.LinkType;
import org.ostis.scmemory.model.element.link.ScLink;
import org.ostis.scmemory.model.element.node.ScNode;
import org.ostis.scmemory.model.exception.ScMemoryException;
import org.ostis.scmemory.model.pattern.factory.DefaultScPattern5Factory;
import org.ostis.scmemory.model.pattern.pattern5.ScConstruction5;
import org.phls.personalTrainer.web.scmemory.connection.ScConnection;
import org.phls.personalTrainer.web.scmemory.exception.ScException;
import org.phls.personalTrainer.web.scmemory.link.LinkHandler;

import java.util.Optional;

public class LinkHandlerImpl implements LinkHandler {
    private static final LinkHandlerImpl HOLDER_INSTANCE = new LinkHandlerImpl();

    private LinkHandlerImpl() {
    }

    public static LinkHandlerImpl getInstance() {
        return HOLDER_INSTANCE;
    }

    @Override
    public Optional<ScLink> getLinkContentThroughRelation(ScNode node, ScNode relation) throws ScException {
        try (ScConnection connection = new ScConnection()) {
            var quintuple = connection.find(DefaultScPattern5Factory.get(
                    node,
                    EdgeType.D_COMMON_VAR,
                    LinkType.LINK_VAR,
                    EdgeType.ACCESS_VAR_POS_PERM,
                    relation)
            ).findFirst();

            return quintuple.map(ScConstruction5::get3);
        } catch (ScException e) {
            throw e;
        } catch (ScMemoryException e) {
            throw new ScException("ScMemory exception", e);
        } catch (Exception e) {
            throw new ScException("Memory open exception!", e);
        }
    }
}
