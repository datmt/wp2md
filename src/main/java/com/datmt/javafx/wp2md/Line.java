package com.datmt.javafx.wp2md;

public class Line {
   private String content;

    public Line(String content) {
        this.content = content;
    }

    public boolean isStartLine() {
        return content.trim().startsWith("<!-- wp");
    }


    public boolean isEndLine() {
        return content.trim().startsWith("<!-- /wp");
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return getContent();
    }

    public String getContent() {
        return content;
    }


}