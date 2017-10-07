package com.collibra.interview.backend.server.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.function.Consumer;

public class Nodes implements Iterable<Node> {

    private List<Node> nodes = new ArrayList();

    public Node getNodeByName(String nodeName) {
        int nodeIndex = nodes.indexOf(Node.fromName(nodeName));
        if (nodeIndex == -1) {
            return null;
        }
        return nodes.get(nodeIndex);
    }

    public boolean add(String nodeName) {
        if(doesNotExists(nodeName)) {
            nodes.add(new Node(nodeName));
            return true;
        }
        return false;
    }

    private boolean doesNotExists(String nodeName) {
        return !nodes.contains(new Node(nodeName));
    }

    public boolean remove(Node node) {
        return nodes.remove(node);
    }

    public boolean contains(Node node) {
        return nodes.contains(node);
    }

    public void clear() {
        nodes.clear();
    }

    @Override
    public Iterator<Node> iterator() {
        return nodes.iterator();
    }

    @Override
    public void forEach(Consumer<? super Node> action) {
        nodes.forEach(action);
    }

    @Override
    public Spliterator<Node> spliterator() {
        return nodes.spliterator();
    }
}
