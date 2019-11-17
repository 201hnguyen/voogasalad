package voogasalad;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.security.Signature;

public class testGAETower extends Application {
    public static final String TITLE = "TestFX Example";
    public static final int SIZE = 300;
    public static final boolean DEBUG = true;

    public void start (Stage primaryStage) {
        GAETowerView newTowerPage = new GAETowerView();
        //primaryStage.setTitle(TITLE);
        //primaryStage.setScene(new Scene(makeRoot(),SIZE,SIZE));
        //primaryStage.show();

    }

    private Group makeRoot ()  {
        Group root = new Group();
        Label appTitle = new Label("Testing GAE Tower");
        appTitle.setLayoutX(150);
        appTitle.setLayoutY(50);
        root.getChildren().add(appTitle);
        return root;
    }



    public static void main(String[] args) {
        launch(args);
    }
}
