package org.phls.personalTrainer.web.scmemory.extractor.impl;

import org.ostis.scmemory.model.element.edge.EdgeType;
import org.ostis.scmemory.model.element.node.NodeType;
import org.ostis.scmemory.model.element.node.ScNode;
import org.phls.personalTrainer.web.model.impl.Diet;
import org.phls.personalTrainer.web.scmemory.exception.ScException;
import org.phls.personalTrainer.web.scmemory.extractor.ScEntityExtractor;
import org.phls.personalTrainer.web.scmemory.node.Products;
import org.phls.personalTrainer.web.scmemory.node.RelationNodes;
import org.phls.personalTrainer.web.scmemory.util.CommonUtils;
import org.phls.personalTrainer.web.scmemory.util.CommonUtilsImpl;

import java.util.List;

public class DietExtractor implements ScEntityExtractor<Diet> {
    private static final DietExtractor HOLDER_INSTANCE = new DietExtractor();
    private static final CommonUtils utils = CommonUtilsImpl.getInstance();

    private DietExtractor() {
    }

    public static DietExtractor getInstance() {
        return HOLDER_INSTANCE;
    }

    @Override
    public Diet extractEntity(ScNode entityNode) throws ScException {

        var breakfast = utils.receiveTripleWithRelationTarget(
                entityNode,
                EdgeType.D_COMMON_CONST,
                NodeType.CONST_TUPLE,
                EdgeType.ACCESS_CONST_POS_PERM,
                RelationNodes.NREL_BREAKFAST.getNode());

        var lunch = utils.receiveTripleWithRelationTarget(
                entityNode,
                EdgeType.D_COMMON_CONST,
                NodeType.CONST_TUPLE,
                EdgeType.ACCESS_CONST_POS_PERM,
                RelationNodes.NREL_LUNCH.getNode());

        var dinner = utils.receiveTripleWithRelationTarget(
                entityNode,
                EdgeType.D_COMMON_CONST,
                NodeType.CONST_TUPLE,
                EdgeType.ACCESS_CONST_POS_PERM,
                RelationNodes.NREL_DINNER.getNode());

        if (breakfast.isEmpty() || lunch.isEmpty() || dinner.isEmpty())
            throw new ScException("Can't found products for food intake");

        var productsNodeForBreakfast = utils.receiveAllFromSet(breakfast.get(), NodeType.CONST);
        var productsNodeForLunch = utils.receiveAllFromSet(lunch.get(), NodeType.CONST);
        var productsNodeForDinner = utils.receiveAllFromSet(dinner.get(), NodeType.CONST);

        List<Products> productsForBreakfast = productsNodeForBreakfast.stream()
                .map(productNode -> Products.getInstance((ScNode) productNode))
                .toList();
        List<Products> productsForLunch = productsNodeForLunch.stream()
                .map(productNode -> Products.getInstance((ScNode) productNode))
                .toList();
        List<Products> productsForDinner = productsNodeForDinner.stream()
                .map(productNode -> Products.getInstance((ScNode) productNode))
                .toList();

        return new Diet(productsForBreakfast, productsForLunch, productsForDinner);
    }
}
