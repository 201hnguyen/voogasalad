package voogasalad.gameplayer.GUI;

import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.*;
import voogasalad.gameengine.api.ActionsProcessor;
import voogasalad.gameengine.api.Engine;
import voogasalad.gameengine.api.GameSceneObject;
import voogasalad.gameengine.executors.exceptions.GameEngineException;
import voogasalad.gameengine.executors.sprites.Sprite;
import voogasalad.gameengine.executors.utils.SpriteArchetype;

import java.util.List;

public class DisplayScreen extends Pane {
    private ActionsProcessor actionsProcessor;
    private int currentImageID;

    public DisplayScreen(ActionsProcessor actionsProcessor, Engine engine) {
        this.actionsProcessor = actionsProcessor;
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
                try {
                    GameSceneObject gso = engine.execute(0);
                    updateDisplayScreen(gso.getOnScreenSprites());
                } catch (GameEngineException e) {
                    e.printStackTrace();
                }
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
        ImageView toDisplay = (ImageView) toLoad.getImage();
        int xPos = (int) sprite.getX();
        int yPos = (int) sprite.getY();
        addImageToScreen(toDisplay, xPos, yPos);
        if (sprite.getSpriteArchetype() == SpriteArchetype.TOWER) {
            toDisplay.setOnMouseClicked((MouseEvent e) -> {

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
