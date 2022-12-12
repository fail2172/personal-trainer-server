package org.phls.personalTrainer.web.scmemory.extractor;

import org.ostis.scmemory.model.element.node.ScNode;
import org.phls.personalTrainer.web.scmemory.exception.ScException;

import java.util.Optional;

public interface IdExtractor {
    Integer extractId(ScNode entityNode) throws ScException;
}
