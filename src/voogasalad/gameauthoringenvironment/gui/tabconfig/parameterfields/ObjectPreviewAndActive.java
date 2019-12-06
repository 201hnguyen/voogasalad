package voogasalad.gameauthoringenvironment.gui.tabconfig.parameterfields;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.Map;

public class ObjectPreviewAndActive extends BorderPane{
    private VBox vBox;
    private int windowHeight;
    private int windowWidth;

    public ObjectPreviewAndActive(Map<String, String> objectContentMap, int windowHeightParam, int windowWidthParam){
        windowHeight = windowHeightParam;
        windowWidth = windowWidthParam;
        vBox = new VBox(10);
        vBox.setPrefHeight(windowHeight);
        vBox.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, null, null)));
        addToVBox(objectContentMap);
        ScrollPane scrollPane = new ScrollPane(vBox);
        scrollPane.setPrefHeight(windowHeight);
        scrollPane.setMaxHeight(scrollPane.getPrefHeight());
        scrollPane.setFitToWidth(true);
        this.setTop(scrollPane);
    }

    private void addToVBox(Map<String, String> objectContentMap){
        Label title = new Label("Properties");
        title.setFont(Font.font("Verdana", 40));
        vBox.getChildren().add(title);
        for(String key : objectContentMap.keySet()){
            String value = objectContentMap.get(key);
            if(objectContentMap.get(key) == null || objectContentMap.get(key).isEmpty()){
                value = "This value has not been set";
            }
            Label fieldLabel = new Label(key + " : " + value);
            vBox.getChildren().add(fieldLabel);
        }
        Button makeActive = new Button("Click Here to Activate");
        makeActive.setStyle("-fx-background-color: #00ff00; -fx-border-color:black;");
        makeActive.setPrefWidth(windowWidth);
        vBox.getChildren().add(makeActive);
    }
}
