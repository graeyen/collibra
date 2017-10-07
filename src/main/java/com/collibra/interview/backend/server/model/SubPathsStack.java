package com.collibra.interview.backend.server.model;

import java.util.ArrayDeque;
import java.util.Deque;

public class SubPathsStack {

    private final Deque<SubPath> subPaths = new ArrayDeque();

    public void push(SubPath subPath) {
        subPaths.addFirst(subPath);
    }

    public SubPath pop() {
        return subPaths.removeFirst();
    }

    public boolean isEmpty() {
        return subPaths.isEmpty();
    }
}
