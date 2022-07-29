package com.datmt.javafx.wp2md;

import java.util.List;

public class Article {

    private String mdContent;
    List<Block> blocks;
    public Article(List<Block> blocks) {
        this.blocks = blocks;
    }

    public Article(String article) {

    }


    public String getMdContent() {
        StringBuilder build = new StringBuilder();
        for (Block b : blocks) {
            build.append(b.toMarkdown()).append("\n");
        }

        return build.toString();
    }
}
