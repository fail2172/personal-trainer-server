package org.phls.personalTrainer.web.scmemory.node;

import org.ostis.scmemory.model.element.node.ScNode;
import org.phls.personalTrainer.web.scmemory.util.CommonUtils;
import org.phls.personalTrainer.web.scmemory.util.CommonUtilsImpl;

public enum Products {
    CONCEPT_TEA("concept_tea", "tea"),
    CONCEPT_CHEESE("concept_cheese", "cheese"),
    CONCEPT_BROCCOLI("concept_broccoli", "broccoli"),
    CONCEPT_OMELET("concept_omelet", "omelet"),
    CONCEPT_COD("concept_cod", "cod"),
    CONCEPT_GREENERY("concept_greenery", "greenery"),
    CONCEPT_VEGETABLES("concept_vegetables", "vegetables"),
    CONCEPT_TURKEY("concept_turkey", "turkey"),
    CONCEPT_WHOLE_GRAIN_PORRIDGE("concept_whole_grain_porridge", "whole_grain_porridge"),
    CONCEPT_BOILED_EGGS("concept_boiled_eggs", "boiled_eggs"),
    CONCEPT_EGG_WHITES("concept_egg_whites", "egg_whites"),
    CONCEPT_SALAD_OF_GREENS("concept_salad_of_greens", "salad_of_greens"),
    CONCEPT_CHICKEN("concept_chicken", "chicken"),
    CONCEPT_NUTS("concept_nuts", "nuts"),
    CONCEPT_BUCKWHEAT("concept_buckwheat", "buckwheat"),
    CONCEPT_RICE("concept_rice", "rice"),
    CONCEPT_CURD("concept_curd", "curd"),
    CONCEPT_FISH("concept_fish", "fish"),
    CONCEPT_CHICKEN_FILLET("concept_chicken_fillet", "chicken_fillet");

    private final ScNode node;
    private final String name;

    Products(String systemIdtf, String name) {
        this.name = name;
        try {
            CommonUtils utils = CommonUtilsImpl.getInstance();
            node = utils.receiveNode(systemIdtf);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Products getInstance(ScNode node) {
        for(Products productNode : values()){
            if (productNode.getNode().equals(node))
                return productNode;
        }
        return null;
    }

    public ScNode getNode() {
        return node;
    }
}
