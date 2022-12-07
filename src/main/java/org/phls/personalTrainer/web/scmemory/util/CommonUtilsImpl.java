package org.phls.personalTrainer.web.scmemory.util;

import org.ostis.scmemory.model.element.ScElement;
import org.ostis.scmemory.model.element.edge.EdgeType;
import org.ostis.scmemory.model.element.edge.ScEdge;
import org.ostis.scmemory.model.element.node.NodeType;
import org.ostis.scmemory.model.element.node.ScNode;
import org.ostis.scmemory.model.exception.ScMemoryException;
import org.ostis.scmemory.model.pattern.factory.DefaultScPattern3Factory;
import org.phls.personalTrainer.web.scmemory.connection.ScConnection;
import org.phls.personalTrainer.web.scmemory.exception.ScException;
import org.phls.personalTrainer.web.scmemory.pattern.SetPattern;
import org.phls.personalTrainer.web.scmemory.pattern.TriplePattern;
import org.phls.personalTrainer.web.scmemory.pattern.impl.SetPatternImpl;
import org.phls.personalTrainer.web.scmemory.pattern.impl.TriplePatternImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

public class CommonUtilsImpl implements CommonUtils {
    private static final CommonUtilsImpl HOLDER_INSTANCE = new CommonUtilsImpl();
    private static final TriplePattern triplePattern = TriplePatternImpl.getInstance();
    private static final SetPattern setPattern = SetPatternImpl.getInstance();
    private final Map<EdgeType, EdgeType> edgesVars;
    private final Map<NodeType, NodeType> nodesVars;

    private CommonUtilsImpl() {
        edgesVars = new HashMap<>();
        edgesVars.put(EdgeType.D_COMMON, EdgeType.D_COMMON_VAR);
        edgesVars.put(EdgeType.U_COMMON, EdgeType.U_COMMON_VAR);
        edgesVars.put(EdgeType.D_COMMON_CONST, EdgeType.D_COMMON_VAR);
        edgesVars.put(EdgeType.U_COMMON_CONST, EdgeType.U_COMMON_VAR);
        edgesVars.put(EdgeType.ACCESS, EdgeType.ACCESS_VAR_POS_PERM);
        edgesVars.put(EdgeType.ACCESS_CONST_POS_PERM, EdgeType.ACCESS_VAR_POS_PERM);
        edgesVars.put(EdgeType.ACCESS_CONST_FUZ_PERM, EdgeType.ACCESS_VAR_FUZ_PERM);
        edgesVars.put(EdgeType.ACCESS_CONST_NEG_PERM, EdgeType.ACCESS_VAR_NEG_PERM);
        edgesVars.put(EdgeType.ACCESS_CONST_POS_TEMP, EdgeType.ACCESS_VAR_POS_TEMP);
        edgesVars.put(EdgeType.ACCESS_CONST_FUZ_TEMP, EdgeType.ACCESS_VAR_FUZ_TEMP);
        edgesVars.put(EdgeType.ACCESS_CONST_NEG_TEMP, EdgeType.ACCESS_VAR_NEG_TEMP);

        nodesVars = new HashMap<>();
        nodesVars.put(NodeType.CONST, NodeType.VAR);
        nodesVars.put(NodeType.CONST_ABSTRACT, NodeType.VAR_ABSTRACT);
        nodesVars.put(NodeType.CONST_CLASS, NodeType.VAR_CLASS);
        nodesVars.put(NodeType.CONST_MATERIAL, NodeType.VAR_MATERIAL);
        nodesVars.put(NodeType.CONST_NO_ROLE, NodeType.VAR_NO_ROLE);
        nodesVars.put(NodeType.CONST_ROLE, NodeType.VAR_ROLE);
        nodesVars.put(NodeType.CONST_STRUCT, NodeType.VAR_STRUCT);
        nodesVars.put(NodeType.CONST_TUPLE, NodeType.VAR_TUPLE);
    }

    public static CommonUtilsImpl getInstance() {
        return HOLDER_INSTANCE;
    }

    @Override
    public boolean checkEdge(ScNode begin, ScNode end, EdgeType edgeType) throws ScException {
        try (ScConnection connection = new ScConnection()) {
            var pattern = DefaultScPattern3Factory.get(begin, edgesVars.get(edgeType), end);
            var result = connection.find(pattern);
            return result.findAny().isPresent();
        } catch (ScMemoryException e) {
            throw new ScException("ScMemory exception", e);
        } catch (Exception e) {
            throw new ScException("Memory open exception!", e);
        }
    }

    @Override
    public void createTripleWithRelation(
            ScElement param1, EdgeType param2, ScElement param3, EdgeType param4, ScElement param5)
            throws ScException {
        try (ScConnection connection = new ScConnection()) {
            ScEdge edge = connection.createEdge(param2, param1, param3);
            connection.createEdge(param4, param5, edge);
        } catch (ScMemoryException e) {
            throw new ScException("ScMemory exception", e);
        } catch (Exception e) {
            throw new ScException("Memory open exception!", e);
        }
    }

    @Override
    public ScNode receiveNode(String systemIdtf) throws ScException {
        try (ScConnection connection = new ScConnection()) {
            var nodeOptional = connection.findKeynode(systemIdtf);

            if (nodeOptional.isEmpty())
                throw new ScException(String.format("Node with system id(%s) not found", systemIdtf));

            return nodeOptional.get();
        } catch (ScException e) {
            throw e;
        } catch (ScMemoryException e) {
            throw new ScException("ScMemory exception", e);
        } catch (Exception e) {
            throw new ScException("Memory open exception!", e);
        }
    }

    @Override
    public Optional<ScNode> receiveTripleBegin(NodeType beginType, EdgeType edgeType, ScNode target)
            throws ScException {
        try (ScConnection connection = new ScConnection()) {
            var pattern = triplePattern
                    .receiveTripleWithTargetPattern(nodesVars.get(beginType), edgesVars.get(edgeType), target);
            var triple = connection.find(pattern).findFirst();

            return triple.map(stream -> (ScNode) stream.toList().get(TriplePatternImpl.BEGIN_INDEX));
        } catch (ScException e) {
            throw e;
        } catch (ScMemoryException e) {
            throw new ScException("ScMemory exception", e);
        } catch (Exception e) {
            throw new ScException("Memory open exception!", e);
        }
    }

    @Override
    public Optional<ScNode> receiveTripleTarget(ScNode begin, EdgeType edgeType, NodeType targetType)
            throws ScException {
        try (ScConnection connection = new ScConnection()) {
            var pattern = triplePattern
                    .receiveTripleWithBeginPattern(begin, edgesVars.get(edgeType), nodesVars.get(targetType));
            var triple = connection.find(pattern).findFirst();

            return triple.map(stream -> (ScNode) stream.toList().get(TriplePatternImpl.TARGET_INDEX));
        } catch (ScException e) {
            throw e;
        } catch (ScMemoryException e) {
            throw new ScException("ScMemory exception", e);
        } catch (Exception e) {
            throw new ScException("Memory open exception!", e);
        }
    }

    @Override
    public List<? extends ScElement> receiveAllFromSet(ScNode set, NodeType elementsType) throws ScException {
        try (ScConnection connection = new ScConnection()) {
            var pattern = setPattern.receiveSetPattern(set, nodesVars.get(elementsType));

            return connection.find(pattern)
                    .map(triple -> triple.toList().get(SetPatternImpl.SET_ELEMENT_INDEX))
                    .toList();
        } catch (ScException e) {
            throw e;
        } catch (ScMemoryException e) {
            throw new ScException("ScMemory exception", e);
        } catch (Exception e) {
            throw new ScException("Memory open exception!", e);
        }
    }
}
