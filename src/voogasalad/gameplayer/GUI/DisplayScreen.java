package voogasalad.gameplayer.GUI;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.*;
import voogasalad.gameengine.api.UIActionsProcessor;
import voogasalad.gameengine.executors.sprites.Sprite;

import java.util.List;

public class DisplayScreen extends Pane {

    public DisplayScreen(UIActionsProcessor uiActionsProcessor) {
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
                System.out.println("Dropped");
                success = true;
            }
            event.setDropCompleted(success);
            // TODO: uiActionsProcessor.processAddSpriteAction();
            ImageView image = new ImageView(db.getImage());
            image.setLayoutX(event.getX());
            image.setLayoutY(event.getY());
            this.getChildren().add(image);
            event.consume();
        });
    }

    public void updateDisplayScreen(List<Sprite> sprites){
        this.getChildren().clear();
        for(Sprite sprite: sprites){
            loadInSprite(sprite);
        }
    }

    private void loadInSprite(Sprite sprite) {
        Sprite toLoad = sprite;
        ImageView toDisplay = (ImageView) toLoad.getImage();
        int xPos = (int) sprite.getX();
        int yPos = (int) sprite.getY();
        addImageToScreen(toDisplay, xPos, yPos);
        // TODO: figure out how we will pass in the height and width
    }

    private void addImageToScreen(ImageView image, int xPos, int yPos) {
        //TODO: Check why this has to be like this
        image.setX(xPos);
        image.setY(yPos);
        getChildren().add(image);
    }

}
