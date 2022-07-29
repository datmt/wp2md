package com.datmt.javafx.wp2md.model.block;

import com.datmt.javafx.wp2md.Block;
import com.datmt.javafx.wp2md.Line;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;

import java.util.List;

public class ImageBlock extends Block {
    public ImageBlock(List<Line> lines) {
        super(lines);
    }

    @Override
    public String toMarkdown() {
        Element element = Jsoup.parse(getContent()).selectFirst("img");

        assert element != null;

        return "!["+element.attr("alt")+"]("+element.attr("src")+")";
    }
}
