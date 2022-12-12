package org.phls.personalTrainer.web.scmemory.link;

import org.ostis.scmemory.model.element.link.ScLink;
import org.ostis.scmemory.model.element.node.ScNode;
import org.phls.personalTrainer.web.scmemory.exception.ScException;

import java.util.Optional;

public interface LinkHandler {
    Optional<ScLink> getLinkContentThroughRelation(ScNode node, ScNode relation) throws ScException;
}
