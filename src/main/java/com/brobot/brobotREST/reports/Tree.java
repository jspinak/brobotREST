package com.brobot.brobotREST.reports;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

public class Tree {

    private String name;
    private List<Tree> children = new ArrayList<>();

    public Tree(String name) {
        this.name = name;
    }

    public Tree(String name, List<Tree> children) {
        this.children = children;
    }

    public void addChild(Tree child) {
        children.add(child);
    }
}
