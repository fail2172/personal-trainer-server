package org.phls.personalTrainer.web.scmemory.extractor;

import org.ostis.scmemory.model.element.node.ScNode;
import org.phls.personalTrainer.web.scmemory.exception.ScException;

public interface ScEntityExtractor {
    ScNode extractScEntity(ScNode contour) throws ScException;
}
