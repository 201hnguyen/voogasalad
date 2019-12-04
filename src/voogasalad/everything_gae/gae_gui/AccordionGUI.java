package voogasalad.everything_gae.gae_gui;

import voogasalad.everything_gae.gae_gui.gae_levelcomponents.GAE_LevelConfigPage;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

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
    private VBox myAccordionVBox;



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
        myAccordionVBox = new VBox();
        TitledPane tPane = new TitledPane();
        tPane.setContent(myAccordionVBox);
        return tPane;
    }

    private Button createAccordionAddButton(){
        Button addButton = new Button("+");
        addButton.setOnMouseClicked(event -> {
            if(gameObjectName.equals("MapConfig")){
                GAE_LevelConfigPage newLevelPage = new GAE_LevelConfigPage();
            }
            else {
                try {
                    newObjectPage = new CreateObjectParameters(gameObjectName, properties, paramFieldType, myAccordionVBox);
                } catch (ParserConfigurationException e) {
                    e.printStackTrace();
                }
            }

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
