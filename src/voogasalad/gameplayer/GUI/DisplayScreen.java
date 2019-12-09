package voogasalad.gameplayer.GUI;

import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.*;
import voogasalad.gameengine.api.ActionsProcessor;
import voogasalad.gameengine.executors.sprites.Sprite;
import voogasalad.gameengine.executors.utils.SpriteArchetype;
import voogasalad.gameplayer.Player;

import java.util.List;
import java.util.ResourceBundle;

public class DisplayScreen extends Pane {

    private SelectedTowerPane selectedTowerPane;
    private ActionsProcessor actionsProcessor;
    private int currentImageID;
    private Player myPlayer;
    private PlayerVisualization myPlayerVisualization;


    public DisplayScreen(ActionsProcessor actionsProcessor, Player player, SelectedTowerPane selectedTowerPane, PlayerVisualization playerVisualization) {
        this.myPlayer = player;
        this.myPlayerVisualization = playerVisualization;
        this.actionsProcessor = actionsProcessor;
        this.selectedTowerPane = selectedTowerPane;
        this.setOnDragOver((DragEvent event) -> {
            Dragboard db = event.getDragboard();
            if (db.hasImage()) {
                event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
            }
            event.consume();
        });
        this.setOnDragDropped((DragEvent event) -> {
            Dragboard db = event.getDragboard();
            boolean success = false;
            if (db.hasImage()) {
                success = true;
                this.actionsProcessor.processAddSpriteAction(currentImageID, event.getX(), event.getY());
                myPlayer.executeEngineWithZeroElapsedTime();
            }
            event.setDropCompleted(success);
            event.consume();
        });
    }

    public void updateDisplayScreen(List<Sprite> sprites){
        this.getChildren().clear();
        for(Sprite sprite: sprites){
            loadInSprite(sprite);
        }
    }

    public void setImageDraggedID(int id){
        currentImageID = id;
    }

    private void loadInSprite(Sprite sprite) {
        //        ImageView toDisplay = new ImageView(new Image(toLoad.getImagePath()));
        ImageView toDisplay = (ImageView) sprite.getImage();
        int xPos = (int) sprite.getX();
        int yPos = (int) sprite.getY();
        addImageToScreen(toDisplay, xPos, yPos);
        if (sprite.getSpriteArchetype() == SpriteArchetype.TOWER) {
            toDisplay.setOnMouseClicked(e -> {
                if(!sprite.getHasBeenClicked()) {
                    myPlayerVisualization.pauseButtonAction();
                    selectedTowerPane.removeTower(sprite, xPos, yPos);
                    sprite.setHasBeenClicked(true);
                }
            });
        }
        // TODO: figure out how we will pass in the height and width
    }

    private void addImageToScreen(ImageView image, int xPos, int yPos) {
        //TODO: Check why this has to be like this
        double imageWidth = image.getBoundsInLocal().getWidth();
        double imageHeight = image.getBoundsInLocal().getHeight();
        boolean inBounds = (xPos < getBoundsInLocal().getWidth() - imageWidth/2 && yPos < getBoundsInLocal().getHeight() - imageHeight/2 && xPos > imageWidth/2 && yPos > imageHeight/2);
        if(inBounds) {
            image.setX(xPos - imageWidth/2);
            image.setY(yPos - imageHeight/2);
            getChildren().add(image);
        }
    }

}
