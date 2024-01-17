module com.meow.monopoly {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.ikonli.javafx;
    requires org.yaml.snakeyaml;

    opens com.meow.monopoly.fxapp to javafx.fxml;
    exports com.meow.monopoly.fxapp;
}