package gae_gui;

import gae_gui.gae_Tower.GAETowerView;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;

import java.lang.reflect.Method;

public class AccordionGUI extends Accordion {
    String[] properties;

    public AccordionGUI (String gameObjectName, String gameObjectProperties, GUI_Controller target) {
        properties = gameObjectProperties.split(",");
        TitledPane titledPane = makeAccordion(properties);
        BorderPane bPane = new BorderPane();
        bPane.setPrefWidth(200);
        bPane.setCenter(new Label(gameObjectName));
        bPane.setRight(createAccordionAddButton());
        titledPane.setGraphic(bPane);
        getPanes().addAll(titledPane);
    }

    private TitledPane makeAccordion(String[] gameObjectProperties) {
        VBox vBox = new VBox();
        TitledPane tPane = new TitledPane();
        for (int j = 0; j < gameObjectProperties.length; j++){
            vBox.getChildren().add(new Label(gameObjectProperties[j]));
            //create appropriate text field based on what the input is ... dropdown for X, file selector for image, etc.
            vBox.getChildren().add(new TextField());
            //
        }
        tPane.setContent(vBox);
        return tPane;
    }

    //Has to create the right button for each category
    private Button createAccordionAddButton(){
        Button addButton = new Button("+");
        addButton.setOnMouseClicked(event -> {
            GAETowerView newTowerPage = new GAETowerView(properties);
        });
        return addButton;
    }

}
