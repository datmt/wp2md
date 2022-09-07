package com.datmt.javafx.wp2md;

import com.datmt.javafx.wp2md.model.block.CodeBlock;
import com.datmt.javafx.wp2md.model.block.HeadingBlock;
import com.datmt.javafx.wp2md.model.block.ImageBlock;
import com.datmt.javafx.wp2md.model.block.ListBlock;
import com.datmt.javafx.wp2md.model.block.ParagraphBlock;
import com.datmt.javafx.wp2md.model.block.VideoBlock;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConverterTest {

    private static String content;
    private static String headingText;
    private static String paragraphText;
    private static String imageText;
    private static String codeText;
    private static String listText;

    private static String videoText;

    @BeforeAll
    static void setup() {
        content = """
                <!-- wp:codemirror-blocks/code-block {"mode":"stex","mime":"text/x-latex"} -->
                <div class="wp-block-codemirror-blocks-code-block code-block"><pre>server.port=9988
                spring.application.name=config-server
                spring.profiles.active=native, git
                spring.cloud.config.server.native.search-locations=classpath:/config
                spring.cloud.config.server.git.uri=https://github.com/datmt/Quick-Sample-private.git
                spring.cloud.config.server.git.search-paths=config-server/config
                spring.cloud.config.server.git.username=my_temp_access_token
                spring.cloud.config.server.git.password=ghp_D6AlGb0f9CY......D5XG0UqzJB</pre></div>
                <!-- /wp:codemirror-blocks/code-block -->
                               
                <!-- wp:paragraph -->
                <p>As you can see on lines 7 and 8, both username and password are needed. The password is the access token, obviously. The username is the name of the token.</p>
                <!-- /wp:paragraph -->
                               
                <!-- wp:paragraph -->
                <p>Now, if we reload the Spring cloud config server, we can see the settings again:</p>
                <!-- /wp:paragraph -->
                               
                <!-- wp:image {"id":614,"sizeSlug":"large","linkDestination":"none"} -->
                <figure class="wp-block-image size-large"><img src="https://datmt.com/wp-content/uploads/2022/07/image-21-1024x550.png" alt="Configure private git access for spring cloud config server" class="wp-image-614"/><figcaption>Configure private git access for the spring cloud config server</figcaption></figure>
                <!-- /wp:image -->
                               
                <!-- wp:paragraph -->
                <p></p>
                <!-- /wp:paragraph -->
                              
                <!-- wp:heading -->
                <h2>Enable Configuration Encryption for Security</h2>
                <!-- /wp:heading -->
                              
                <!-- wp:codemirror-blocks/code-block {"mode":"stex","mime":"text/x-latex"} -->
                <div class="wp-block-codemirror-blocks-code-block code-block"><pre>server.port=9988
                spring.application.name=config-server
                spring.profiles.active=native, git
                spring.cloud.config.server.native.search-locations=classpath:/config
                spring.cloud.config.server.git.uri=https://github.com/datmt/quick-samples.git
                spring.cloud.config.server.git.search-paths=config-server/config
                encrypt.key=SUPER_SECRET_ENCRYPT_KEY</pre></div>
                <!-- /wp:codemirror-blocks/code-block -->
                """;
        headingText = """
                                <!-- wp:heading -->
                <h2>Enable Configuration Encryption for Security</h2>
                <!-- /wp:heading -->
 
                """;
        paragraphText = """
                               <!-- wp:paragraph -->
                <p>As you can see on lines 7 and 8, both username and password are needed. The password is the access token, obviously. The username is the name of the token.</p>
                <!-- /wp:paragraph -->
  
                """;

        imageText = """
                                <!-- wp:image {"id":614,"sizeSlug":"large","linkDestination":"none"} -->
                <figure class="wp-block-image size-large"><img src="https://datmt.com/wp-content/uploads/2022/07/image-21-1024x550.png" alt="Configure private git access for spring cloud config server" class="wp-image-614"/><figcaption>Configure private git access for the spring cloud config server</figcaption></figure>
                <!-- /wp:image -->
 
                """;

        codeText = """
                                <!-- wp:codemirror-blocks/code-block {"mode":"stex","mime":"text/x-latex"} -->
                <div class="wp-block-codemirror-blocks-code-block code-block"><pre>server.port=9988
                spring.application.name=config-server
                spring.profiles.active=native, git
                spring.cloud.config.server.native.search-locations=classpath:/config
                spring.cloud.config.server.git.uri=https://github.com/datmt/quick-samples.git
                spring.cloud.config.server.git.search-paths=config-server/config
                encrypt.key=SUPER_SECRET_ENCRYPT_KEY</pre></div>
                <!-- /wp:codemirror-blocks/code-block -->
                """;
       listText = """
               <!-- wp:list -->
               <ul><li>Use $unwind to deconstruct the investment array</li><li>Use $group by _id and $sum to calculate the total investment value of each person. In this step, we also use the accumulator <code>$first</code> to extract the family name and store it in a field called <code>family_name</code></li><li>Use $match to filter out people with less than 50 investment values</li><li>Use $group on last name and $sum to count the number of the remaining people.</li></ul>
               <!-- /wp:list -->
               """;

       videoText = """
               <!-- wp:video -->
               <figure class="wp-block-video"><video controls src="https://datmt.com/wp-content/uploads/2022/08/2022-08-01-14-34-51.mp4"></video><figcaption>Import documents to collections</figcaption></figure>
               <!-- /wp:video -->
               """;
    }
    @Test
    void string2Blocks() {
        var blocks = Converter.string2Blocks(content) ;
        assertEquals(8, blocks.size());
        for (Block b : blocks) {
            if (b instanceof HeadingBlock)
                System.out.println(b.toMarkdown());
        }
    }

    @Test
    void heading2Md() {
        var block = Converter.string2Blocks(headingText).get(0);
        assertTrue(block instanceof HeadingBlock); ;
        System.out.println(block.toMarkdown());
        assertTrue(block.toMarkdown().contains("Enable Configuration"));
    }
    @Test
    void list2Md() {
        var block = Converter.string2Blocks(listText).get(0);
        assertTrue(block instanceof ListBlock); ;
        System.out.println(block.toMarkdown());
        assertTrue(block.toMarkdown().contains("deconstruct"));
    }
    @Test
    void video2Md() {
        var block = Converter.string2Blocks(videoText).get(0);
        assertTrue(block instanceof VideoBlock); ;
        System.out.println(block.toMarkdown());
        assertTrue(block.toMarkdown().contains("mp4"));
    }
    @Test
    void paragraph2Md() {
        var block = Converter.string2Blocks(paragraphText);
        assertEquals(1, block.size());
        assertTrue(block.get(0) instanceof ParagraphBlock); ;
        assertTrue(block.get(0).toMarkdown().contains("lines 7"));
    }

    @Test
    void image2Md() {
        var block = Converter.string2Blocks(imageText);
        assertEquals(1, block.size());
        assertTrue(block.get(0) instanceof ImageBlock); ;
        assertTrue(block.get(0).toMarkdown().contains("wp-content"));
        System.out.println(block.get(0).toMarkdown());
    }

    @Test
    void code2Md() {
        var block = Converter.string2Blocks(codeText);
        assertEquals(1, block.size());
        assertTrue(block.get(0) instanceof CodeBlock); ;
        System.out.println(block.get(0).toMarkdown());
    }
}