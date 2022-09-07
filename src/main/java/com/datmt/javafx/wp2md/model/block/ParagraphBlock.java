package com.datmt.javafx.wp2md.model.block;

import com.datmt.javafx.wp2md.Block;
import com.datmt.javafx.wp2md.Line;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;

import java.util.List;

public class ParagraphBlock extends Block {
    public ParagraphBlock(List<Line> lines) {
        super(lines);
    }

    @Override
    public String toMarkdown() {
        Element element = Jsoup.parse(getContent()).selectFirst("p");

        assert element != null;
        return element.html()
                .replaceAll("<code>", "`")
                .replaceAll("</code>", "`")
                .replaceAll("<strong>", "**")
                .replaceAll("</strong>", "**")
                .replaceAll("</em>", "*")
                .replaceAll("<em>", "*");
    }
}
