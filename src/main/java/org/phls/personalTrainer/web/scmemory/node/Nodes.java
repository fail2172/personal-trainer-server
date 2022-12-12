package org.phls.personalTrainer.web.scmemory.node;

import org.ostis.scmemory.model.element.node.ScNode;
import org.phls.personalTrainer.web.scmemory.util.CommonUtils;
import org.phls.personalTrainer.web.scmemory.util.CommonUtilsImpl;

public enum Nodes {
    QUESTION_FINISHED_UNSUCCESSFULLY("question_finished_unsuccessfully"),
    QUESTION_FINISHED_SUCCESSFULLY("question_finished_successfully"),
    CONCEPT_EATING_FROM_TRAINER("concept_eating_from_trainer"),
    QUESTION_INITIATED("question_initiated"),
    CONCEPT_USER("concept_user");

    private final ScNode node;

    Nodes(String systemIdtf) {
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
