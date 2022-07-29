package com.datmt.javafx.wp2md.model.block;

import com.datmt.javafx.wp2md.Block;
import com.datmt.javafx.wp2md.Line;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Tag;

import java.util.List;

public class HeadingBlock extends Block {
    public HeadingBlock(List<Line> lines) {
        super(lines);
    }

    @Override
    public String toMarkdown() {
        var content = getContent();
        Element element = Jsoup.parse(content);
        if (element.select("h1").size() > 0)
            return "# " + element.text().trim();
        else if (element.select("h2").size() > 0)
            return "## " + element.text().trim();
        else if (element.select("h3").size() > 0)
            return "### " + element.text().trim();
        else if (element.select("h4").size() > 0)
            return "#### " + element.text().trim();

        return "#### " + element.text().trim();
    }
}
