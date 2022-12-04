package org.phls.personalTrainer.web.scmemory.connection;

import org.ostis.api.context.DefaultScContext;
import org.ostis.scmemory.model.ScMemory;
import org.ostis.scmemory.model.element.ScElement;
import org.ostis.scmemory.model.element.edge.EdgeType;
import org.ostis.scmemory.model.element.edge.ScEdge;
import org.ostis.scmemory.model.element.link.*;
import org.ostis.scmemory.model.element.node.NodeType;
import org.ostis.scmemory.model.element.node.ScNode;
import org.ostis.scmemory.model.exception.ScMemoryException;
import org.ostis.scmemory.model.pattern.ScPattern;
import org.ostis.scmemory.model.pattern.pattern3.ScConstruction3;
import org.ostis.scmemory.model.pattern.pattern3.ScPattern3;
import org.ostis.scmemory.model.pattern.pattern5.ScConstruction5;
import org.ostis.scmemory.model.pattern.pattern5.ScPattern5;
import org.ostis.scmemory.websocketmemory.memory.SyncOstisScMemory;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.util.Optional;
import java.util.Properties;
import java.util.stream.Stream;

public class ScConnection implements AutoCloseable {
    private static final String GRADLE_PROPERTIES_FILE_NAME = "gradle.properties";
    private static final String PORT_PROPERTY_NAME = "port";

    private final SyncOstisScMemory memory;
    private final DefaultScContext context;

    public ScConnection() throws Exception {
        try (final FileInputStream stream = new FileInputStream(GRADLE_PROPERTIES_FILE_NAME)) {
            final Properties properties = new Properties();
            properties.load(stream);
            memory = new SyncOstisScMemory(new URI(properties.getProperty(PORT_PROPERTY_NAME)));
            memory.open();
            context = new DefaultScContext(memory);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File not found", e);
        } catch (IOException e) {
            throw new RuntimeException("Error while reading file", e);
        }
    }

    @Override
    public void close() throws Exception {
        memory.close();
    }

    public ScMemory getMemory() {
        return context.getMemory();
    }

    public ScNode createNode(NodeType type) throws ScMemoryException {
        return context.createNode(type);
    }

    public Stream<? extends ScNode> createNodes(Stream<NodeType> types) throws ScMemoryException {
        return context.createNodes(types);
    }

    public ScEdge createEdge(EdgeType type, ScElement source, ScElement target) throws ScMemoryException {
        return context.createEdge(type, source, target);
    }

    public Stream<ScEdge> createEdges(Stream<EdgeType> types, Stream<? extends ScElement> sources, Stream<? extends ScElement> targets) throws ScMemoryException {
        return context.createEdges(types, sources, targets);
    }

    public ScLinkInteger createIntegerLink(LinkType type, Integer content) throws ScMemoryException {
        return context.createIntegerLink(type, content);
    }

    public ScLinkFloat createFloatLink(LinkType type, Float content) throws ScMemoryException {
        return context.createFloatLink(type, content);
    }

    public ScLinkString createStringLink(LinkType type, String content) throws ScMemoryException {
        return context.createStringLink(type, content);
    }

    public ScLinkBinary createBinaryLink(LinkType type, ByteArrayOutputStream content) throws ScMemoryException {
        return context.createBinaryLink(type, content);
    }

    public Boolean deleteElement(ScElement element) throws ScMemoryException {
        return context.deleteElement(element);
    }

    public Boolean deleteElements(Stream<? extends ScElement> elements) throws ScMemoryException {
        return context.deleteElements(elements);
    }

    public Stream<? extends ScEdge> findAllConstructionsNodeEdgeNode(ScNode fixedNode, EdgeType edge, NodeType node) throws ScMemoryException {
        return context.findAllConstructionsNodeEdgeNode(fixedNode, edge, node);
    }

    public <t1 extends ScElement, t2, T3 extends ScElement> Stream<? extends ScConstruction3<t1, T3>> find(ScPattern3<t1, t2, T3> pattern) throws ScMemoryException {
        return context.find(pattern);
    }

    public <t1 extends ScElement, t2, t3, T2 extends ScElement, T3 extends ScElement> Stream<? extends ScConstruction5<t1, T2, T3>> find(ScPattern5<t1, t2, t3, T2, T3> pattern) throws ScMemoryException {
        return context.find(pattern);
    }

    public Stream<Stream<? extends ScElement>> find(ScPattern pattern) throws ScMemoryException {
        return context.find(pattern);
    }

    public Boolean setIntegerLinkContent(ScLinkInteger link, Integer content) throws ScMemoryException {
        return context.setIntegerLinkContent(link, content);
    }

    public Boolean setFloatLinkContent(ScLinkFloat link, Float content) throws ScMemoryException {
        return context.setFloatLinkContent(link, content);
    }

    public Boolean setStringLinkContent(ScLinkString link, String content) throws ScMemoryException {
        return context.setStringLinkContent(link, content);
    }

    public Boolean setBinaryLinkContent(ScLinkBinary link, ByteArrayOutputStream content) throws ScMemoryException {
        return context.setBinaryLinkContent(link, content);
    }

    public Integer getIntegerLinkContent(ScLinkInteger link) throws ScMemoryException {
        return context.getIntegerLinkContent(link);
    }

    public Float getFloatLinkContent(ScLinkFloat link) throws ScMemoryException {
        return context.getFloatLinkContent(link);
    }

    public String getStringLinkContent(ScLinkString link) throws ScMemoryException {
        return context.getStringLinkContent(link);
    }

    public ByteArrayOutputStream getBinaryLinkContent(ScLinkBinary link) throws ScMemoryException {
        return context.getBinaryLinkContent(link);
    }

    public Optional<? extends ScNode> findKeynode(String idtf) throws ScMemoryException {
        return context.findKeynode(idtf);
    }

    public ScNode resolveKeynode(String idtf, NodeType type) throws ScMemoryException {
        return context.resolveKeynode(idtf, type);
    }
}
