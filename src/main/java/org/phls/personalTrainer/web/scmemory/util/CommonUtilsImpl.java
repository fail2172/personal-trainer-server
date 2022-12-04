package org.phls.personalTrainer.web.scmemory.util;

import org.ostis.scmemory.model.element.ScElement;
import org.ostis.scmemory.model.element.edge.EdgeType;
import org.ostis.scmemory.model.element.edge.ScEdge;
import org.ostis.scmemory.model.element.node.ScNode;
import org.ostis.scmemory.model.exception.ScMemoryException;
import org.ostis.scmemory.model.pattern.factory.DefaultScPattern3Factory;
import org.phls.personalTrainer.web.scmemory.connection.ScConnection;
import org.phls.personalTrainer.web.scmemory.exception.ScException;

import java.util.HashMap;
import java.util.Map;

public class CommonUtilsImpl implements CommonUtils {
    private static final CommonUtilsImpl HOLDER_INSTANCE = new CommonUtilsImpl();
    private final Map<EdgeType, EdgeType> edgesVars;

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
}
