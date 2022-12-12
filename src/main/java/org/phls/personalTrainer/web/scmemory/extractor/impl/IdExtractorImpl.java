package org.phls.personalTrainer.web.scmemory.extractor.impl;

import org.ostis.scmemory.model.element.link.ScLinkString;
import org.ostis.scmemory.model.element.node.ScNode;
import org.ostis.scmemory.model.exception.ScMemoryException;
import org.phls.personalTrainer.web.scmemory.connection.ScConnection;
import org.phls.personalTrainer.web.scmemory.exception.ScException;
import org.phls.personalTrainer.web.scmemory.extractor.IdExtractor;
import org.phls.personalTrainer.web.scmemory.link.LinkHandler;
import org.phls.personalTrainer.web.scmemory.link.impl.LinkHandlerImpl;
import org.phls.personalTrainer.web.scmemory.node.RelationNodes;

public class IdExtractorImpl implements IdExtractor {
    private static final IdExtractorImpl HOLDER_INSTANCE = new IdExtractorImpl();
    private static final LinkHandler linkHandler = LinkHandlerImpl.getInstance();

    private IdExtractorImpl() {
    }

    public static IdExtractorImpl getInstance(){
        return HOLDER_INSTANCE;
    }

    @Override
    public Integer extractId(ScNode entityNode) throws ScException {
        try (ScConnection connection = new ScConnection()){
            var idLink = linkHandler.getLinkContentThroughRelation(entityNode, RelationNodes.NREL_ID.getNode());

            if(idLink.isEmpty())
                throw new ScException("Failed to retrieve entity id");

            final String idLinkValue = connection.getStringLinkContent((ScLinkString) idLink.get());

            return Integer.parseInt(idLinkValue);
        } catch (ScException e) {
            throw e;
        } catch (ScMemoryException e) {
            throw new ScException("ScMemory exception", e);
        } catch (Exception e) {
            throw new ScException("Memory open exception!", e);
        }
    }
}
