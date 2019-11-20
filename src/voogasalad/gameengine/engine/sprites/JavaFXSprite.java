package voogasalad.gameengine.engine.sprites;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import voogasalad.gameengine.engine.exceptions.GameEngineException;
import voogasalad.gameengine.engine.sprites.strategies.health.HealthStrategy;
import voogasalad.gameengine.engine.sprites.strategies.movement.MovementStrategy;

import java.awt.*;

public class JavaFXSprite implements Sprite {
    private int mySpriteId;
    private Point currentPosition;
    private HealthStrategy myHealthStrategy;
    private MovementStrategy myMovementStrategy;
    private String myImagePath;
    private ImageView myImageView;
    private SpriteBuilder myOriginalBuilder;

    public JavaFXSprite(SpriteBuilder builder) {
        myOriginalBuilder = builder;
        mySpriteId = builder.getSpriteId();
        currentPosition = new Point();
        currentPosition.setLocation(builder.getX(), builder.getY());
        myHealthStrategy = builder.getHealthStrategy();
        myMovementStrategy = builder.getMovementStrategy();
        myImagePath = builder.getImagePath();
    }

    @Override
    public Sprite makeClone(double x, double y, int spriteId) throws GameEngineException {
        return new SpriteBuilder().setSpriteId(spriteId).setX(x).setY(y)
                .setHealthStrategy(myOriginalBuilder.getHealthStrategy())
                .setHeight(myOriginalBuilder.getHeight())
                .setImagePath(myOriginalBuilder.getImagePath())
                .setMovementStrategy(myOriginalBuilder.getMovementStrategy())
                .setWidth(myOriginalBuilder.getWidth())
                .build();
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
    public String getImage() {
        return myImagePath;
    }

    public int getHealth() {
        return myHealthStrategy.getHealth();
    }

    public void updatePosition(double elapsedTime) {
        currentPosition = myMovementStrategy.calculateNextPosition(elapsedTime, currentPosition);
    }

    public double getHeight(){
        return height;
    }

    public double getWidth(){
        return width;
    }
}
