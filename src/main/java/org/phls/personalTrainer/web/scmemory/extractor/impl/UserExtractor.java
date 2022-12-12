package org.phls.personalTrainer.web.scmemory.extractor.impl;

import org.ostis.scmemory.model.element.edge.EdgeType;
import org.ostis.scmemory.model.element.link.ScLinkString;
import org.ostis.scmemory.model.element.node.NodeType;
import org.ostis.scmemory.model.element.node.ScNode;
import org.ostis.scmemory.model.exception.ScMemoryException;
import org.phls.personalTrainer.web.model.impl.User;
import org.phls.personalTrainer.web.scmemory.connection.ScConnection;
import org.phls.personalTrainer.web.scmemory.exception.ScException;
import org.phls.personalTrainer.web.scmemory.extractor.ScEntityExtractor;
import org.phls.personalTrainer.web.scmemory.node.DiseaseNodes;
import org.phls.personalTrainer.web.scmemory.pattern.EntityPattern;
import org.phls.personalTrainer.web.scmemory.pattern.impl.UserPattern;
import org.phls.personalTrainer.web.scmemory.util.CommonUtils;
import org.phls.personalTrainer.web.scmemory.util.CommonUtilsImpl;

import java.util.List;
import java.util.Optional;

public class UserExtractor implements ScEntityExtractor<User> {
    private static final UserExtractor HOLDER_INSTANCE = new UserExtractor();
    private static final EntityPattern entityPattern = UserPattern.getInstance();
    private static final CommonUtils utils = CommonUtilsImpl.getInstance();

    private UserExtractor() {
    }

    public static UserExtractor getInstance() {
        return HOLDER_INSTANCE;
    }

    @Override
    public User extractEntity(ScNode userNode) throws ScException {
        try (ScConnection connection = new ScConnection()) {
            var userPattern = entityPattern.receivePattern(userNode);
            var userElements = connection.find(userPattern).findFirst();

            if (userElements.isEmpty())
                throw new ScException("Failed to get user parameters");

            var userParameters = userElements.get().toList();

            ScLinkString loginLink = (ScLinkString) userParameters.get(UserPattern.LOGIN_INDEX);
            ScLinkString passwordLink = (ScLinkString) userParameters.get(UserPattern.PASSWORD_INDEX);

            final String login = connection.getStringLinkContent(loginLink);
            final String password = connection.getStringLinkContent(passwordLink);

            User user = new User(login, password);
            Optional<ScNode> diseaseSet =
                    utils.receiveTripleTarget(userNode, EdgeType.D_COMMON_CONST, NodeType.CONST_TUPLE);

            return diseaseSet.isPresent()
                    ? user.withDiseases(receiveUserDiseases(diseaseSet.get()))
                    : user;
        } catch (ScException e) {
            throw e;
        } catch (ScMemoryException e) {
            throw new ScException("ScMemory exception", e);
        } catch (Exception e) {
            throw new ScException("Memory open exception!", e);
        }
    }

    private List<DiseaseNodes> receiveUserDiseases(ScNode diseaseSet) throws ScException {
        return utils.receiveAllFromSet(diseaseSet, NodeType.CONST).stream()
                .map(diseaseNode -> DiseaseNodes.getInstance((ScNode) diseaseNode))
                .toList();
    }
}
