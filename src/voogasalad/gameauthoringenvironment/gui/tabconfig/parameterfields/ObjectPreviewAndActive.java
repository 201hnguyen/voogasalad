package voogasalad.gameauthoringenvironment.gui.tabconfig.parameterfields;

import javafx.geometry.Pos;
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
    private Map<String, String> objectContentMap;
    private String gameObjectName;
    private Stage windowStage;
    private Map<String, Map<String, String>> activeObjects;
    private Button icon;
    private Button makeActive;

    public ObjectPreviewAndActive(String gameObjectNameParam, Map<String, String> objectContentMapParam, int windowHeightParam,
                                  int windowWidthParam, Stage windowStageParam, Map<String, Map<String, String>> activeObjectsParam, Button iconParam){
        icon = iconParam;
        activeObjects = activeObjectsParam;
        windowStage = windowStageParam;
        gameObjectName = gameObjectNameParam;
        objectContentMap = objectContentMapParam;
        windowHeight = windowHeightParam;
        windowWidth = windowWidthParam;
        styleVBox();
        addToVBox();
        ScrollPane scrollPane = configureScrollPane();
        this.setTop(scrollPane);
    }

    private void addToVBox(){
        Label title = formatTitleLabel();
        vBox.getChildren().add(title);
        addMapProperties();
        if(activeObjects.containsKey(gameObjectName)){
            makeActive = styleDeactivateButton();
        }
        else{
            makeActive = styleActivateButton();
        }
        vBox.getChildren().add(makeActive);
    }

    private Label formatTitleLabel(){
        Label title = new Label("Properties");
        title.setPrefWidth(windowWidth);
        title.setAlignment(Pos.CENTER);
        title.setFont(Font.font("Verdana", 40));
        return title;
    }

    private void addMapProperties(){
        for(String key : objectContentMap.keySet()){
            String value = objectContentMap.get(key);
            if(objectContentMap.get(key) == null || objectContentMap.get(key).isEmpty()){
                value = "This value has not been set";
            }
            Label fieldLabel = new Label(key + " : " + value);
            vBox.getChildren().add(fieldLabel);
        }
    }

    private Button styleActivateButton(){
        Button makeActive = new Button("Click Here to Activate");
        makeActive.setStyle("-fx-background-color: #00ff00; -fx-border-color:black;");
        makeActive.setPrefWidth(windowWidth);
        makeActive.setOnMouseClicked(event -> {
            addToActive();
        });
        return makeActive;
    }

    private Button styleDeactivateButton(){
        Button makeActive = new Button("Click Here to Deactivate");
        makeActive.setStyle("-fx-background-color: #ff6666; -fx-border-color:black;");
        makeActive.setPrefWidth(windowWidth);
        makeActive.setOnMouseClicked(event -> {
            removeFromActive();
        });
        return makeActive;
    }

    private ScrollPane configureScrollPane(){
        ScrollPane scrollPane = new ScrollPane(vBox);
        scrollPane.setPrefHeight(windowHeight);
        scrollPane.setMaxHeight(scrollPane.getPrefHeight());
        scrollPane.setFitToWidth(true);
        return scrollPane;
    }

    private void styleVBox(){
        vBox = new VBox(10);
        vBox.setPrefHeight(windowHeight);
        vBox.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, null, null)));
    }

    public void addToActive(){
        activeObjects.put(gameObjectName, objectContentMap);
        icon.setStyle("-fx-background-color: #00ff00; -fx-border-color:black;");
        windowStage.close();
    }

    public void removeFromActive(){
        activeObjects.remove(gameObjectName, objectContentMap);
        icon.setStyle(null);
        windowStage.close();
    }
}