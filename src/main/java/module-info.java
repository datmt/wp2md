module com.datmt.javafx.wp2md {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.jsoup;
    requires static lombok;
    requires org.apache.commons.text;

    opens com.datmt.javafx.wp2md to javafx.fxml;
    exports com.datmt.javafx.wp2md;
}