package voogasalad.gameplayer.GUI;

import javafx.geometry.Insets;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import voogasalad.gameengine.executors.sprites.Sprite;

import java.util.List;

public class DisplayScreen extends Pane {

    public static final int FRAMES_PER_SECOND = 60;
    public static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
    private List<Sprite> spriteList;

    public DisplayScreen(List<Sprite> sprites) {
        setBackground(new Background(new BackgroundFill(Color.GHOSTWHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        spriteList = sprites;
        for(Sprite sprite: spriteList){
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
