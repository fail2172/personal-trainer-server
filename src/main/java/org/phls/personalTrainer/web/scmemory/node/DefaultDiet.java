package org.phls.personalTrainer.web.scmemory.node;

import org.ostis.scmemory.model.element.node.ScNode;
import org.phls.personalTrainer.web.scmemory.util.CommonUtils;
import org.phls.personalTrainer.web.scmemory.util.CommonUtilsImpl;

public enum DefaultDiet {
    CONCEPT_DRYING_EATING("concept_drying_eating"),
    CONCEPT_HEALTHY_EATING("concept_healthy_eating"),
    CONCEPT_MASS_EATING("concept_mass_eating");

    private final ScNode node;

    DefaultDiet(String systemIdtf) {
        try {
            CommonUtils utils = CommonUtilsImpl.getInstance();
            node = utils.receiveNode(systemIdtf);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public ScNode getNode() {
        return node;
    }
}
