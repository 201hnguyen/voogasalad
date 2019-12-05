package voogasalad.everything_gae.bus;

import voogasalad.everything_gae.gae_gui.*;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.w3c.dom.Document;
import voogasalad.everything_gae.gae_gui.level_map_config.level_config.LevelConfigScene;
import voogasalad.gameengine.executors.exceptions.GameEngineException;
import voogasalad.gameplayer.Player;

public class Bus {
    private Stage currentStage;
    private int width;
    private int height;
    private BorderPane root;
    private Scene busScene;
    private Scene gaeScene;
    private Scene gamePlayerScene;
    private GUI_Controller gaeGuiController;
    private GUI_SceneMaker gaeObject;
    private Document createdXML;
    private Group busRoot;
    private LevelConfigScene levelConfigScene;

    public Bus(Stage currentStageParam, BorderPane rootParam, int widthParam, int heightParam){
        currentStage = currentStageParam;
        root = rootParam;
        width = widthParam;
        height = heightParam;
        gaeObject = new GUI_SceneMaker(widthParam, heightParam, this);
        levelConfigScene = new LevelConfigScene();

    }

    /**
     * This method should return frontend to chose GAE or GamePlayer
     */
    public Scene getBusScene(){
        return createBusScene();
    }

    public Scene createBusScene(){
        busRoot = new Group();
        busRoot.getChildren().add(changeToGAEButton());
        //busRoot.getChildren().add(changeToGamePlayerButton());
        return new Scene(busRoot, width, height);
    }

    private Button changeToGAEButton(){
        Button myButton = new Button("Enter GAE");
        myButton.setOnMouseClicked(event -> {
            changeToGAE();
            //FOR TESTING
            //currentStage.setScene(levelConfigScene.getScene(root));
        });
        return myButton;
    }

    public void changeToGAE(){
        currentStage.setScene(gaeObject.createGAEScene(root));
    }


    public void goToPlayer(Document createdXML) throws GameEngineException {
        Player player = new Player(currentStage, createdXML);
    }


    //not doing anything yet
    private Button changeToGamePlayerButton(){
        Button myButton = new Button("Load File and Play Game");
        myButton.setOnMouseClicked(event -> {
            changeToGAE();
        });
        return myButton;
    }

}
