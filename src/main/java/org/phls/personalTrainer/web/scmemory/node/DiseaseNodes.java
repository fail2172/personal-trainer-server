package org.phls.personalTrainer.web.scmemory.node;

import org.ostis.scmemory.model.element.node.ScNode;
import org.phls.personalTrainer.web.scmemory.exception.ScException;
import org.phls.personalTrainer.web.scmemory.util.CommonUtils;
import org.phls.personalTrainer.web.scmemory.util.CommonUtilsImpl;

public enum DiseaseNodes {
    CONCEPT_DIASTASIS("concept_diastasis"),
    CONCEPT_SCOLIOSIS("concept_scoliosis"),
    CONCEPT_HERNIATED_DISC("concept_herniated_disc"),
    CONCEPT_MENISCUS_INJURY("concept_meniscus_injury"),
    CONCEPT_VARICOSE_VEINS("concept_varicose_veins");

    private final ScNode node;

    DiseaseNodes(String systemIdtf) {
        try {
            CommonUtils utils = CommonUtilsImpl.getInstance();
            node = utils.receiveNode(systemIdtf);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static DiseaseNodes getInstance(ScNode node) {
        for(DiseaseNodes diseaseNode : values()){
            if (diseaseNode.getNode() == node)
                return diseaseNode;
        }
        return null;
    }

    public ScNode getNode() {
        return node;
    }
}
