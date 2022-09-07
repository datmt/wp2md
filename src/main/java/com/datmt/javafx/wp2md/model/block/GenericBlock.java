package com.datmt.javafx.wp2md.model.block;

import com.datmt.javafx.wp2md.Block;
import com.datmt.javafx.wp2md.Line;

import java.util.List;

public class GenericBlock extends Block {
    public GenericBlock(List<Line> lines) {
        super(lines);
    }

    @Override
    public String toMarkdown() {
        StringBuilder builder = new StringBuilder();
        for (Line line: lines) {
            if (line.isStartLine() || line.isEndLine())
                continue;
            builder.append(line.getContent());
        }
        return builder.toString();
    }
}
