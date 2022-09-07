package com.datmt.javafx.wp2md.model.block;

import com.datmt.javafx.wp2md.Block;
import com.datmt.javafx.wp2md.Line;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;

import java.util.List;

public class VideoBlock extends Block {
    public VideoBlock(List<Line> lines) {
        super(lines);
    }

    @Override
    public String toMarkdown() {
        Element element = Jsoup.parse(getContent()).selectFirst("video");
        assert element != null;

        return element.outerHtml();

    }
}
