package com.datmt.javafx.wp2md;

import com.datmt.javafx.wp2md.model.block.CodeBlock;
import com.datmt.javafx.wp2md.model.block.GenericBlock;
import com.datmt.javafx.wp2md.model.block.HeadingBlock;
import com.datmt.javafx.wp2md.model.block.ImageBlock;
import com.datmt.javafx.wp2md.model.block.ParagraphBlock;

import java.util.List;

public class BlockClassifier {
    public static Block createBlock(List<Line> lines) {
        var firstLineContent = lines.get(0).getContent();
        if (firstLineContent.contains(Tags.CODE)) {
            return new CodeBlock(lines);
        }
        if (firstLineContent.contains(Tags.PARAGRAPH)) {
            return new ParagraphBlock(lines);
        }
        if (firstLineContent.contains(Tags.HEADING)) {
            return new HeadingBlock(lines);
        }
        if (firstLineContent.contains(Tags.IMAGE)) {
            return new ImageBlock(lines);
        }

        return new GenericBlock(lines);

    }
}
