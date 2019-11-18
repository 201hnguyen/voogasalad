package gae_gui;

import gae_gui.gae_Tower.GAETowerView;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;

import java.util.ResourceBundle;


public class AccordionGUI extends Accordion {
    private int WIDTH = 200;
    private String[] properties;
    private BorderPane bPane;
    private TitledPane tPane;
    private String gameObjectName;
    private ResourceBundle paramFieldType;


    public AccordionGUI (String gameObjectNameParam, String gameObjectProperties, GUI_Controller target, ResourceBundle paramFieldTypeParam) {
        gameObjectName = gameObjectNameParam;
        properties = gameObjectProperties.split(",");
        setMaxWidth(WIDTH);
        tPane = makeAccordion(properties);
        tPane.setGraphic(configureBP(gameObjectName));
        getPanes().addAll(tPane);
        paramFieldType = paramFieldTypeParam;
    }

    private TitledPane makeAccordion(String[] gameObjectProperties) {
        TitledPane tPane = new TitledPane();
        tPane.setContent(new TextArea());
        return tPane;
    }

    private Button createAccordionAddButton(){
        Button addButton = new Button("+");
        addButton.setOnMouseClicked(event -> {
            GAETowerView newTowerPage = new GAETowerView(properties, gameObjectName, paramFieldType);
        });
        return addButton;
    }

    private BorderPane configureBP(String gameObjectName){
        bPane = new BorderPane();
        bPane.setPrefWidth(WIDTH);
        bPane.setCenter(new Label(gameObjectName));
        bPane.setRight(createAccordionAddButton());
        return bPane;
    }
}
