package org.phls.personalTrainer.web.scmemory.pattern;

import org.ostis.scmemory.model.element.node.ScNode;
import org.ostis.scmemory.websocketmemory.memory.pattern.DefaultWebsocketScPattern;

public interface EntityInContourPattern {
    DefaultWebsocketScPattern receivePattern(ScNode contour, ScNode entityClass);
}
