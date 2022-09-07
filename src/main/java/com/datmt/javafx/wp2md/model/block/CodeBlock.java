package com.datmt.javafx.wp2md.model.block;

import com.datmt.javafx.wp2md.Block;
import com.datmt.javafx.wp2md.Converter;
import com.datmt.javafx.wp2md.Line;
import org.apache.commons.text.StringEscapeUtils;

import java.util.List;
import java.util.stream.Collectors;

public class CodeBlock extends Block {
    public CodeBlock(List<Line> lines) {
        super(lines);
    }

    private Line getFirstLine() {
        return lines.get(0);
    }

    private Line getLastLine() {
        return lines.get(lines.size() - 1);
    }

    private String parseLanguage() {
        var lang = getFirstLine().getContent()
                .substring(getFirstLine().getContent().indexOf("\"mime\":") + "\"mime\":".length() + 1)
                .replaceAll("\".*", "");

        lang = lang.substring(lang.indexOf("-") + 1);
        if (lang.contains("ecmascript")) {
            return "javascript";
        }
        return lang;

    }

    @Override
    public String toMarkdown() {
        var contentLines =
                lines.stream()
                        .filter(line -> !line.isStartLine() && !line.isEndLine())
                        .peek(line -> {
                            var lineContent = removeTrailingPre(removeLeadingPre(line.getContent()));
                            line.setContent(lineContent);

                        }).collect(Collectors.toList());

        return "```" + parseLanguage() + "\n" + StringEscapeUtils.unescapeHtml4(Converter.linesToString(contentLines)) + "\n```";
    }

    private String removeLeadingPre(String content) {
        if (!content.contains("<pre>"))
            return content;
        return content.substring(content.indexOf("<pre>") + "<pre>".length());
    }

    private String removeTrailingPre(String content) {
        if (!content.contains("</pre>"))
            return content;
        return content.substring(0, content.indexOf("</pre>"));
    }
}
