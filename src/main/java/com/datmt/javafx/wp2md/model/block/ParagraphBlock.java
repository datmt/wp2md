package com.datmt.javafx.wp2md.model.block;

import com.datmt.javafx.wp2md.Block;
import com.datmt.javafx.wp2md.Line;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;

import java.util.ArrayList;
import java.util.List;

public class ParagraphBlock extends Block {
    public ParagraphBlock(List<Line> lines) {
        super(lines);
    }

    @Override
    public String toMarkdown() {
        Element element = Jsoup.parse(getContent()).selectFirst("p");

        assert element != null;
        var links = element.select("a");

        var mdLinks = new ArrayList<String>();

       for (Element e : links)  {
          var mdL = new StringBuilder() ;
          var linkText = e.text();
          var linkHref = e.attr("href");
          var linkTitle = e.attr("title");

          mdL.append("[").append(linkText).append("](") .append(linkHref)
                  .append(linkTitle.isEmpty() ? linkTitle : " \"" + linkTitle +  "\"")
                  .append(")");

          mdLinks.add(mdL.toString());
       }

       var content = element.html();


       for (int i = 0; i < links.size(); i ++) {
          content = content.replace(links.get(i).outerHtml(), mdLinks.get(i));
       }


        return content
                .replaceAll("<code>", "`")
                .replaceAll("</code>", "`")
                .replaceAll("<strong>", "**")
                .replaceAll("</strong>", "**")
                .replaceAll("</em>", "*")
                .replaceAll("<em>", "*");
    }
}
