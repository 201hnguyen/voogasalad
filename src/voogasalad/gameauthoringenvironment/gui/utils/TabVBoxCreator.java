package voogasalad.gameauthoringenvironment.gui.utils;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class TabVBoxCreator extends VBox{

    public TabVBoxCreator(String vBoxName) {
        Label header = new Label(vBoxName);
        header.setFont(Font.font(14));
        this.getChildren().add(header);
        this.setPrefWidth(200);
        this.setPadding(new Insets(50, 50, 50, 50));
    }
}
