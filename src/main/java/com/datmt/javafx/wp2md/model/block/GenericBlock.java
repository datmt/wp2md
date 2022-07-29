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
        return null;
    }
}
