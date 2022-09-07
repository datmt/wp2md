package com.datmt.javafx.wp2md.model.block;

import com.datmt.javafx.wp2md.Block;
import com.datmt.javafx.wp2md.Line;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;

import java.util.List;

public class ListBlock extends Block {
    public ListBlock(List<Line> lines) {
        super(lines);
    }

    @Override
    public String toMarkdown() {
        Element element = Jsoup.parse(getContent()).selectFirst("ul");
        assert element != null;
        List<Element> lis = element.select("li");

        StringBuilder buffer = new StringBuilder();

        for (Element e : lis) {
            buffer.append("- ").append(e.text()).append("\n");
        }
        return buffer.toString();
    }
}
