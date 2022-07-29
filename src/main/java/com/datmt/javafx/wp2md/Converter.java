package com.datmt.javafx.wp2md;

import com.datmt.javafx.wp2md.model.block.CodeBlock;
import com.datmt.javafx.wp2md.model.block.HeadingBlock;
import com.datmt.javafx.wp2md.model.block.ImageBlock;
import com.datmt.javafx.wp2md.model.block.ParagraphBlock;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Converter {

    public static List<Block> string2Blocks(String content) {
        String[] lines = content.split("\\n");
        boolean inBlock = false;
        var currentBlockLines = new ArrayList<Line>();
        var blocks = new ArrayList<Block>();

        for (int i = 0; i < lines.length; i++) {
            var lineContent = lines[i];
            var line = new Line(lines[i]);
            if (!inBlock && lineContent.isBlank())
                continue;
            if (line.isStartLine()) {
                inBlock = true;
            }
            if (line.isEndLine()) {
                if (!inBlock) //reach the end tag but not in a block, probably invalid document
                    continue;
                inBlock = false;
                currentBlockLines.add(new Line(lineContent));
                blocks.add(BlockClassifier.createBlock(List.copyOf(currentBlockLines)));
                currentBlockLines.clear();
            }

            if (inBlock)
                currentBlockLines.add(new Line(lineContent));

        }

        return blocks;
    }


    public static String linesToString(List<Line> lines) {
        return lines.stream()
                .map(Object::toString)
                .collect(Collectors.joining("\n")).trim();
    }

}
