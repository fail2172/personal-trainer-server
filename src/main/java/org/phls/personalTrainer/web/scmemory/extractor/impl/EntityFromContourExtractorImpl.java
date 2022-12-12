package org.phls.personalTrainer.web.scmemory.extractor.impl;

import org.ostis.scmemory.model.element.node.ScNode;
import org.ostis.scmemory.model.exception.ScMemoryException;
import org.phls.personalTrainer.web.scmemory.connection.ScConnection;
import org.phls.personalTrainer.web.scmemory.exception.ScException;
import org.phls.personalTrainer.web.scmemory.extractor.EntityFromContourExtractor;
import org.phls.personalTrainer.web.scmemory.pattern.EntityInContourPattern;
import org.phls.personalTrainer.web.scmemory.pattern.EntityPattern;
import org.phls.personalTrainer.web.scmemory.pattern.impl.EntityInContourPatternImpl;
import org.phls.personalTrainer.web.scmemory.pattern.impl.UserPattern;
import org.phls.personalTrainer.web.scmemory.util.CommonUtils;
import org.phls.personalTrainer.web.scmemory.util.CommonUtilsImpl;

public class EntityFromContourExtractorImpl implements EntityFromContourExtractor {
    private static final EntityFromContourExtractorImpl HOLDER_INSTANCE = new EntityFromContourExtractorImpl();
    private static final EntityInContourPattern entityInContourPattern = EntityInContourPatternImpl.getInstance();
    private static final EntityPattern entityPattern = UserPattern.getInstance();
    private static final CommonUtils utils = CommonUtilsImpl.getInstance();

    private EntityFromContourExtractorImpl() {
    }

    public static EntityFromContourExtractorImpl getInstance() {
        return HOLDER_INSTANCE;
    }

    @Override
    public ScNode extractScEntity(ScNode contour, ScNode entityClass) throws ScException {
        try (ScConnection connection = new ScConnection()) {
            var entityInContourPattern =
                    EntityFromContourExtractorImpl.entityInContourPattern.receivePattern(contour, entityClass);
            var entityInContour = connection.find(entityInContourPattern).findFirst();

            if (entityInContour.isEmpty())
                throw new ScException("Entity not found not found");

            return (ScNode) entityInContour.get().toList().get(EntityInContourPatternImpl.ENTITY_INDEX);
        } catch (ScException e) {
            throw e;
        } catch (ScMemoryException e) {
            throw new ScException("ScMemory exception", e);
        } catch (Exception e) {
            throw new ScException("Memory open exception!", e);
        }
    }
}
