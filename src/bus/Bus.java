package bus;

import gae_gui.GUI_Controller;
import gae_gui.GUI_SceneMaker;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Bus {
    private Stage currentStage;
    private int width;
    private int height;
    private BorderPane root;
    private Scene gaeScene;
    private GUI_Controller gaeGuiController;
    private GUI_SceneMaker gaeObject;

    public Bus(Stage currentStageParam, BorderPane rootParam, int widthParam, int heightParam){
        currentStage = currentStageParam;
        root = rootParam;
        width = widthParam;
        height = heightParam;
        gaeObject = new GUI_SceneMaker(widthParam, heightParam);

    }

    /**
     * This method should return frontend to chose GAE or GamePlayer
     */
    public Scene getBusScene(){
        //currentStage.setScene(gaeObject.createGAEScene());
        return gaeObject.createGAEScene(root);
    }

    public void changeToGAE(){
        //myGAE.updateXML(myPlayer.getXML);
        currentStage.setScene(gaeObject.createGAEScene(root));
    }

    public void changeToGamePlayer(Scene scene){
        //myPlayer.loadXML(XML)
        currentStage.setScene(scene);
    }

}
