package org.phls.personalTrainer.web.scmemory.node;

import org.ostis.scmemory.model.element.node.ScNode;
import org.phls.personalTrainer.web.scmemory.util.CommonUtils;
import org.phls.personalTrainer.web.scmemory.util.CommonUtilsImpl;

public enum RelationNodes {
    NREL_DISEASES_PARAM("nrel_diseases_param"),
    NREL_USER_PARAMS("nrel_user_params"),
    NREL_PASSWORD("nrel_password"),
    NREL_LOGIN("nrel_login"),
    NREL_BREAKFAST("nrel_breakfast"),
    NREL_LUNCH("nrel_lunch"),
    NREL_DINNER("nrel_dinner"),
    RREL_1("rrel_1"),
    RREL_2("rrel_2"),
    RREL_3("rrel_3");

    private final ScNode node;

    RelationNodes(String systemIdtf) {
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
