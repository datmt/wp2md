package com.datmt.javafx.wp2md;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class AppController {

    @FXML
    TextArea wpSource;

    @FXML
    TextArea mdSource;

    @FXML
    protected void onConvert() {
        var source = wpSource.getText();
        var article = new Article(Converter.string2Blocks(source));
        mdSource.setText(article.getMdContent());
    }
}