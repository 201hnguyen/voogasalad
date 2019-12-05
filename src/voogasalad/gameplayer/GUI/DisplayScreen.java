package voogasalad.gameplayer.GUI;

import javafx.geometry.Insets;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import voogasalad.gameengine.executors.sprites.Sprite;

import java.util.List;

public class DisplayScreen extends Pane {

    public void updateDisplayScreen(List<Sprite> sprites){
        getChildren().clear();
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
        image.setX(xPos - image.getFitWidth()/2);
        image.setY(yPos - image.getFitHeight()/2);
        getChildren().add(image);
    }

}
