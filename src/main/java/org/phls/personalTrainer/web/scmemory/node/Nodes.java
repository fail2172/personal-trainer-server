package org.phls.personalTrainer.web.scmemory.node;

import org.ostis.scmemory.model.element.node.ScNode;
import org.phls.personalTrainer.web.scmemory.util.CommonUtils;
import org.phls.personalTrainer.web.scmemory.util.CommonUtilsImpl;

public enum Nodes {
    QUESTION_FINISHED_UNSUCCESSFULLY("question_finished_unsuccessfully"),
    QUESTION_FINISHED_SUCCESSFULLY("question_finished_successfully"),
    QUESTION_INITIATED("question_initiated"),
    ACTION_CREATE_USER("action_create_user"),
    ACTION_SEARCH_USER("action_search_user"),
    CONCEPT_USER("concept_user"),
    NREL_ANSWER("nrel_answer"),
    RREL_1("rrel_1"),
    RREL_2("rrel_2");

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
