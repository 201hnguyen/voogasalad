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
import java.util.concurrent.atomic.AtomicBoolean;

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
        Sprite toLoad = sprite;
//        ImageView toDisplay = new ImageView(new Image(toLoad.getImagePath()));
        ImageView toDisplay = (ImageView) toLoad.getImage();
        int xPos = (int) sprite.getX();
        int yPos = (int) sprite.getY();
        addImageToScreen(toDisplay, xPos, yPos);
        if (sprite.getSpriteArchetype() == SpriteArchetype.TOWER) {
            AtomicBoolean clicked = new AtomicBoolean(false);
            toDisplay.setOnMouseClicked(e -> {
                if(!clicked.get()) {
                    myPlayerVisualization.pauseButtonAction();
                    selectedTowerPane.removeTower(toLoad, xPos, yPos);
                    clicked.set(true);
                }
            });
        }
        // TODO: figure out how we will pass in the height and width
    }

    private void addImageToScreen(ImageView image, int xPos, int yPos) {
        //TODO: Check why this has to be like this
        image.setX(xPos);
        image.setY(yPos);
        getChildren().add(image);
    }

}
