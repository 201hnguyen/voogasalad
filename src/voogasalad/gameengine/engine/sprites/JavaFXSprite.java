package voogasalad.gameengine.engine.sprites;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import voogasalad.gameengine.engine.exceptions.GameEngineException;
import voogasalad.gameengine.engine.factories.SpriteProductsFactory;
import voogasalad.gameengine.engine.sprites.strategies.health.HealthStrategy;

public class JavaFXSprite implements Sprite {
    private final int mySpriteId;
    private HealthStrategy myHealthStrategy;
    private String myImagePath;
    private ImageView myImageView;

    public JavaFXSprite(double xPos, double yPos, double width, double height, String imagePath, int spriteId, HealthStrategy healthStrategy) {
        mySpriteId = spriteId;
        myHealthStrategy = healthStrategy;
        myImagePath = imagePath;
        Image image = new Image(this.getClass().getClassLoader().getResourceAsStream(myImagePath));
        myImageView = new ImageView(image);
        myImageView.setX(xPos);
        myImageView.setY(yPos);
        myImageView.setFitWidth(width);
        myImageView.setFitHeight(height);
    }

    @Override
    public Sprite makeClone(double x, double y, int spriteId) throws GameEngineException {
        SpriteProductsFactory spriteFactory = new SpriteProductsFactory();
        return spriteFactory.makeSprite(x, y, myImageView.getFitWidth(), myImageView.getFitHeight(), myImagePath, spriteId, myHealthStrategy.makeClone());
    }

    @Override
    public double getX() {
        return myImageView.getX();
    }

    @Override
    public double getY() {
        return myImageView.getY();
    }

    @Override
    public int getId() {
        return mySpriteId;
    }

    public int getHealth() {
        return myHealthStrategy.getHealth();
    }

    @Override
    public Object getImage() {
        return myImageView;
    }
}
