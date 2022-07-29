package com.datmt.javafx.wp2md;

import java.util.List;
import java.util.stream.Collectors;

public abstract class Block {
    protected final List<Line> lines;
    public Block(List<Line> lines) {
        this.lines = lines;
    }


    @Override
    public String toString() {
        var content = new StringBuilder();
        for (Line line : lines) {
            content.append(line.getContent()).append("\n");
        }
        return content.toString();
    }

    public String getContent() {
        return lines.stream().filter(t -> !t.isEndLine() && !t.isStartLine())
                .map(Object::toString)
                .collect(Collectors.joining("\n"));
    }

    public abstract String toMarkdown();
}

