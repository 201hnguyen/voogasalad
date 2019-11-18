package gae_gui;

import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.layout.*;

import java.util.ResourceBundle;

public class GUI_SceneMaker{

    public static final String SPRITE_OPTIONS_RESOURCE = "resources/SpriteOptions";
    public static final String PARAM_FIELD_TYPE_RESOURCE = "resources/ParamToInputType";
    private GUI_Controller myController;
    private ResourceBundle typeToParams;
    private ResourceBundle paramFieldType;
    private int width;
    private int height;
    public GUI_SceneMaker(int widthParam, int heightParam){
        width = widthParam;
        height = heightParam;
        myController = new GUI_Controller();
        typeToParams = ResourceBundle.getBundle(SPRITE_OPTIONS_RESOURCE);
        paramFieldType = ResourceBundle.getBundle(PARAM_FIELD_TYPE_RESOURCE);
    }

    public Scene createGAEScene(BorderPane root){
        Button submitButton = new Button("Submit");
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

//    public void createSubmitButton(VBox root)

}
