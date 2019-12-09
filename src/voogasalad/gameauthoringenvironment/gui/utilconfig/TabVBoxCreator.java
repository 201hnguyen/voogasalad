package voogasalad.gameauthoringenvironment.gui.utilconfig;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;


public class TabVBoxCreator extends VBox{

    ScrollPane myScrollPane;

    public TabVBoxCreator(String vBoxName, Priority priority, double width, double top, double right, double bottom, double left) {
        myScrollPane = new ScrollPane();
        this.setVgrow(myScrollPane, priority);
        Label header = new Label(vBoxName);
        header.setFont(Font.font(14));
        this.getChildren().addAll(header, myScrollPane);
        this.setPrefWidth(width);
        this.setPadding(new Insets(top, right, bottom, left));
    }

    public ScrollPane getScrollPane() {
        return myScrollPane;
    }
}
