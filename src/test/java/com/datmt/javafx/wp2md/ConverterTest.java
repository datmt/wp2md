package com.datmt.javafx.wp2md;

import com.datmt.javafx.wp2md.model.block.CodeBlock;
import com.datmt.javafx.wp2md.model.block.HeadingBlock;
import com.datmt.javafx.wp2md.model.block.ImageBlock;
import com.datmt.javafx.wp2md.model.block.ParagraphBlock;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConverterTest {

    private static String content;
    private static String headingText;
    private static String paragraphText;
    private static String imageText;
    private static String codeText;


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