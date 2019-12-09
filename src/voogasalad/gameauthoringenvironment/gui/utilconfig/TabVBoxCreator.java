package voogasalad.gameauthoringenvironment.gui.utilconfig;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class TabVBoxCreator extends VBox{

    public TabVBoxCreator(String vBoxName, double width, double top, double right, double bottom, double left) {
        Label header = new Label(vBoxName);
        header.setFont(Font.font(14));
        this.getChildren().add(header);
        this.setPrefWidth(width);
        this.setPadding(new Insets(top, right, bottom, left));
    }
}
