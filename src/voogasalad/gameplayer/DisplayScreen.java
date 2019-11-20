package voogasalad.gameplayer;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import voogasalad.gameengine.engine.sprites.JavaFXSprite;
import voogasalad.gameengine.engine.sprites.JavaFXSpriteManager;
import voogasalad.gameengine.engine.sprites.Sprite;

import javax.swing.plaf.BorderUIResource;
import java.util.List;
import java.util.ResourceBundle;

public class DisplayScreen extends Pane {

    public static final int FRAMES_PER_SECOND = 60;
    public static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;

    private String myImagePath = "pandaslogo.png";
    private JavaFXSpriteManager spriteManager; // TODO: will use this to access sprite list - check with team if we are doing this directly or through a controller
    private List<Sprite> spriteList;

    public DisplayScreen() {
//        setMinWidth(600);
//        setMinHeight(600);
        setBackground(new Background(new BackgroundFill(Color.GHOSTWHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        Image image = new Image(this.getClass().getClassLoader().getResourceAsStream(myImagePath));
        ImageView sprite = new ImageView(image);

//        stage.setTitle("VoogaSalad");
//        stage.setScene(displayScene());

        addImageToScreen(sprite, 300, 200, 100, 100); //

//        stage.setResizable(false);
//        stage.show();
    }

    public void loadInSprite(JavaFXSprite jfxSprite) {
        ImageView image = (ImageView) jfxSprite.getImage();
        int xPos = (int) jfxSprite.getX();
        int yPos = (int) jfxSprite.getY();
        int height = 40;
        int width = 40;
        addImageToScreen(image, xPos, yPos, height, width);
        // TODO: figure out how we will pass in the height and width
    }

    public void addImageToScreen(ImageView image, int xPos, int yPos, int height, int width) {
        image.setFitWidth(width);
        image.setFitHeight(height);
        image.setX(xPos - width/2);
        image.setY(yPos - height/2);
        image.setPreserveRatio(true);
        getChildren().add(image);
    }

    /**
     * removes all current sprites from list, sets spriteList to be newSpriteList, and loads them all in
     * @param newSpriteList - updated list of Sprites
     */
    private void updateSpritePositions(List<Sprite> newSpriteList) {
        getChildren().removeAll(spriteList);
        spriteList = newSpriteList;
        spriteList.
                forEach(sprite -> loadInSprite((JavaFXSprite) sprite));
    }

    private void play() {
        var frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> step());
        var animation = new Timeline();
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.getKeyFrames().add(frame);
        animation.play();
    }

    private void step() {
        // TODO: will call update on list of sprite objects - need to access updated list from JavaFXSpriteManager
//        updateSpritePositions();
    }
}
