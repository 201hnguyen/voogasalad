package voogasalad.everything_gae.gae_gui;

import voogasalad.everything_gae.bus.Bus;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import org.w3c.dom.Document;
import voogasalad.gameengine.engine.exceptions.GameEngineException;

import javax.xml.parsers.ParserConfigurationException;
import java.util.ResourceBundle;

public class GUI_SceneMaker{

    public static final String SPRITE_OPTIONS_RESOURCE = "voogasalad/everything_gae/resources/SpriteOptions";
    public static final String PARAM_FIELD_TYPE_RESOURCE = "voogasalad/everything_gae/resources/ParamToInputType";
    private GUI_Controller myController;
    private ResourceBundle typeToParams;
    private ResourceBundle paramFieldType;
    private int width;
    private int height;
    private AddToXML sendToXML;
    public Document createdXML;
    public Bus busInstance;



    public GUI_SceneMaker(int widthParam, int heightParam, Bus myBusInstance){
        sendToXML = new AddToXML();
        myController = new GUI_Controller();
        busInstance = myBusInstance;
        width = widthParam;
        height = heightParam;
        typeToParams = ResourceBundle.getBundle(SPRITE_OPTIONS_RESOURCE);
        paramFieldType = ResourceBundle.getBundle(PARAM_FIELD_TYPE_RESOURCE);
    }

    public Scene createGAEScene(BorderPane root){
        Button submitButton = buttonToCreateXML();
        VBox accordionVBox = createAccordion(new VBox());
        root.setRight(accordionVBox);
        root.setBottom(submitButton);
        return new Scene(root, width,  height);
    }

    public VBox createAccordion(VBox accordionVBox) {
        typeToParams.getKeys().asIterator().forEachRemaining(key -> {
            accordionVBox.getChildren().add(new AccordionGUI(key, typeToParams.getString(key), myController, paramFieldType));
        });
        return accordionVBox;
    }

    private Button buttonToCreateXML(){
        Button myButton = new Button("Submit");
        myButton.setOnMouseClicked(event -> {
            try {
                createdXML = sendToXML.createXML();
                busInstance.goToPlayer(createdXML);

            } catch (ParserConfigurationException | GameEngineException e) {
                e.printStackTrace();
            }
        });
        return myButton;
    }

    public Document getCreatedXML(){
        return createdXML;
    }

}
