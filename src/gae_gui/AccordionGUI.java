package gae_gui;

import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;

import javax.xml.parsers.ParserConfigurationException;
import java.util.ResourceBundle;


public class AccordionGUI extends Accordion {
    private int WIDTH = 200;
    private String[] properties;
    private BorderPane bPane;
    private TitledPane tPane;
    private String gameObjectName;
    private ResourceBundle paramFieldType;
    private CreateObjectParameters newObjectPage;



    public AccordionGUI (String gameObjectNameParam, String gameObjectProperties, GUI_Controller target, ResourceBundle paramFieldTypeParam) {
        gameObjectName = gameObjectNameParam;
        properties = gameObjectProperties.split(",");
        setMaxWidth(WIDTH);
        tPane = makeAccordion();
        tPane.setGraphic(configureBP(gameObjectName));
        getPanes().addAll(tPane);
        paramFieldType = paramFieldTypeParam;
    }

    private TitledPane makeAccordion() {
        TitledPane tPane = new TitledPane();
        tPane.setContent(new TextArea());
        return tPane;
    }

    private Button createAccordionAddButton(){
        Button addButton = new Button("+");
        addButton.setOnMouseClicked(event -> {
            newTowerPage = new CreateObjectParameters(gameObjectName, properties, paramFieldType);
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
