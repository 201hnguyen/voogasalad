package gae_gui;

import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.layout.*;

import java.util.ResourceBundle;

public class GUI_SceneMaker{

    public static final String DEFAULT_RESOURCE_ACTIONS = "resources/SpriteOptions";
    private GUI_Controller myController;
    private ResourceBundle myResources;
    private int width;
    private int height;
    public GUI_SceneMaker(int widthParam, int heightParam){
        width = widthParam;
        height = heightParam;
        myController = new GUI_Controller();
        myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_ACTIONS);

    }

    public Scene createGAEScene(BorderPane root){
        Button submitButton = new Button("Submit");
        VBox accordionVBox = createAccordion(new VBox());
        root.setRight(accordionVBox);
        root.setBottom(submitButton);
//        root.getChildren().add
//        root.getChildren().add(accordionVBox);
//        root.getChildren().add(submitButton);
        return new Scene(root, width,  height);
    }

    public VBox createAccordion(VBox accordionVBox) {
        myResources.getKeys().asIterator().forEachRemaining(key -> {
            accordionVBox.getChildren().add(new AccordionGUI(key, myResources.getString(key), myController));
        });
        return accordionVBox;
    }

//    public void createSubmitButton(VBox root)

}
