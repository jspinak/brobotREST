package com.brobot.brobotREST.manageStates;

import com.brobot.brobotREST.primatives.enums.StateEnum;
import lombok.Data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Data
public class Path {

    private List<StateEnum> path = new ArrayList<>();
    private int score; // lower scores are chosen first when selecting a path

    public boolean equals(Path path) {
        return this.path.equals(path.getPath());
    }

    public int size() {
        return path.size();
    }

    public StateEnum get(int i) {
        return path.get(i);
    }

    public void add(StateEnum stateEnum) {
        path.add(stateEnum);
    }

    public boolean contains(StateEnum stateEnum) {
        return path.contains(stateEnum);
    }

    public void reverse() {
        Collections.reverse(path);
    }

    public boolean remove(StateEnum stateEnum) {
        return path.remove(stateEnum);
    }

    public Path getCopy() {
        Path p = new Path();
        p.setPath(new ArrayList<>(path));
        p.setScore(score);
        return p;
    }

    public void print() {
        System.out.print("path");
        path.forEach(stateEnum -> System.out.format("-> %s ", stateEnum));
    }
}
