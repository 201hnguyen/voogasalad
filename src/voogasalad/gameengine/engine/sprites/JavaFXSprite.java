package voogasalad.gameengine.engine.sprites;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import voogasalad.gameengine.engine.exceptions.GameEngineException;
import voogasalad.gameengine.engine.factories.SpriteProductsFactory;
import voogasalad.gameengine.engine.sprites.strategies.health.HealthStrategy;
import voogasalad.gameengine.engine.sprites.strategies.movement.MovementStrategy;

import java.awt.*;

public class JavaFXSprite implements Sprite {
    private final int mySpriteId;
    private Point currentPosition;
    private HealthStrategy myHealthStrategy;
    private MovementStrategy myMovementStrategy;
    private String myImagePath;
    private ImageView myImageView;

    public JavaFXSprite(double xPos, double yPos, double width, double height, String imagePath, int spriteId, HealthStrategy healthStrategy, MovementStrategy movementStrategy) {
        mySpriteId = spriteId;
        currentPosition = new Point();
        currentPosition.setLocation(xPos, yPos);
        myHealthStrategy = healthStrategy;
        myImagePath = imagePath;
        Image image = new Image(this.getClass().getClassLoader().getResourceAsStream(myImagePath));
        myImageView = new ImageView(image);
        myImageView.setFitWidth(width);
        myImageView.setFitHeight(height);
    }

    @Override
    public Sprite makeClone(double x, double y, int spriteId) throws GameEngineException {
        SpriteProductsFactory spriteFactory = new SpriteProductsFactory();
        return spriteFactory.makeSprite(x, y, myImageView.getFitWidth(), myImageView.getFitHeight(), myImagePath, spriteId, myHealthStrategy.makeClone(), myMovementStrategy.makeClone());
    }

    @Override
    public double getX() {
        return currentPosition.getX();
    }

    @Override
    public double getY() {
        return currentPosition.getY();
    }

    @Override
    public int getId() {
        return mySpriteId;
    }

    @Override
    public Object getImage() {
        return myImageView;
    }

    public int getHealth() {
        return myHealthStrategy.getHealth();
    }

    public void updatePosition(double elapsedTime) {
        currentPosition = myMovementStrategy.calculateNextPosition(elapsedTime, currentPosition);
    }
}
