package voogasalad.gameauthoringenvironment.bus;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import org.xml.sax.SAXException;
import voogasalad.gameauthoringenvironment.gui.*;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.w3c.dom.Document;
import voogasalad.gameengine.executors.exceptions.GameEngineException;
import voogasalad.gameplayer.Player;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.function.Consumer;
import java.util.function.Function;

public class Bus {
    private Stage currentStage;
    private int width;
    private int height;
    private BorderPane root;
    private Scene busScene;
    private Scene gaeScene;
    private Scene gamePlayerScene;
    private SceneCreator gaeObject;
    private Document createdXML;
    private VBox busRoot;

    public Bus(Stage currentStageParam, BorderPane rootParam, int widthParam, int heightParam){
        currentStage = currentStageParam;
        root = rootParam;
        width = widthParam;
        height = heightParam;
        gaeObject = new SceneCreator(widthParam, heightParam, this);

    }

    /**
     * This method should return frontend to chose GAE or GamePlayer
     */
    public Scene getBusScene() {
        return createBusScene();
    }

    public Scene createBusScene() {
        busRoot = new VBox();
        busRoot.getChildren().add(createMenuButton("newgame.png", "newgame-hover.png", e -> changeToGAE()));
        busRoot.getChildren().add(createMenuButton("loadgame.png", "loadgame-hover.png", e -> {
            try {
                loadGameHandler();
            } catch (GameEngineException ex) {
                ex.printStackTrace();
            }
        }));

        //busRoot.getChildren().add(changeToGamePlayerButton());
        return new Scene(busRoot, width, height);
    }

//    private Label changeToGAEButton(){
//
////        myButton.setOnMouseClicked(event -> {
////            changeToGAE();
////            //FOR TESTING
////            //currentStage.setScene(levelConfigScene.getScene(root));
////        });
//        return createMenuButton("newgame.png", "newgame-hover.png", e -> changeToGAE());
//    }
//
//    private Label loadGameButton() {
//        return createMenuButton("loadgame.png", "loadgame-hover.png", e -> loadGameHandler());
//    }

    public void changeToGAE(){
        currentStage.setScene(gaeObject.createGAEScene(root));
    }

    private Label createMenuButton(String imagePath, String imagePathHover, Consumer consumer) {
        Label myButton = new Label();
        ImageView image = new ImageView(new Image(imagePath));
        ImageView imageHover = new ImageView(new Image(imagePathHover));
        image.setFitHeight(50);
        image.setPreserveRatio(true);
        imageHover.setFitHeight(50);
        imageHover.setPreserveRatio(true);
        myButton.setGraphic(image);
        myButton.setOnMouseEntered(e -> myButton.setGraphic(imageHover));
        myButton.setOnMouseExited(e -> myButton.setGraphic(image));
        myButton.setOnMouseClicked(consumer::accept);
        return myButton;
    }


    public void goToPlayer(Document createdXML) throws GameEngineException {
        Player player = new Player(currentStage, createdXML);
    }

    private void loadGameHandler() throws GameEngineException {
        FileChooser fileChooser = new FileChooser();
        File selectedFile = fileChooser.showOpenDialog(currentStage);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        try {
            builder = factory.newDocumentBuilder();
            Document doc = builder.parse(selectedFile);
            goToPlayer(doc);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            throw new GameEngineException(e, "ConfigurationFailedXML");

        }
    }

}
