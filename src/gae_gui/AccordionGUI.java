package gae_gui;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;

import java.lang.reflect.Method;

public class AccordionGUI extends Accordion {

    private Accordion accordion;
    public static final double WIDTH = 100;
    public static final double HEIGHT = 200;

    public AccordionGUI (String gameObjectName, String gameObjectProperties, GUI_Controller target) {
        //super(WIDTH, HEIGHT);
        //accordion = new Accordion();
        String[] properties = gameObjectProperties.split(",");
        //accordion.getPanes().addAll(makeAccordion(gameObjectName, properties));
        getPanes().addAll(makeAccordion(gameObjectName, properties));
    }

    private TitledPane makeAccordion(String gameObjectName, String[] gameObjectProperties) {
        VBox root = new VBox();
        for (int j = 0; j < gameObjectProperties.length; j++){
            root.getChildren().add(new Label(gameObjectProperties[j]));
            //create appropriate text field based on what the input is ... dropdown for X, file selector for image, etc.
            root.getChildren().add(new TextField());
            //
        }
        return new TitledPane(gameObjectName, root);
    }
    // make input field that calls Controller method using reflection as its action
//    private Node makeInputAction (String methodName, GUI_Controller target) {
//
//        result.setOnAction(handler -> {
//            try {
//                // find method with given name that takes String as its only parameter
//                Method m = target.getClass().getDeclaredMethod(methodName, String.class);
//                m.invoke(target);
//            }
//            catch (Exception e) {
//                // FIXME: typically make your own custom exception to throw
//                throw new RuntimeException("Improper configuration", e);
//            }
//        });
//        return result;
//    }

}
