package voogasalad.gameengine.executors.sprites;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import voogasalad.gameengine.executors.control.levelcontrol.LevelActionsRequester;
import voogasalad.gameengine.executors.exceptions.GameEngineException;
import voogasalad.gameengine.executors.objectcreators.SpriteBuilder;
import voogasalad.gameengine.executors.sprites.strategies.attack.AttackStrategy;
import voogasalad.gameengine.executors.sprites.strategies.cost.CostStrategy;
import voogasalad.gameengine.executors.sprites.strategies.health.HealthStrategy;
import voogasalad.gameengine.executors.sprites.strategies.movement.MovementStrategy;
import voogasalad.gameengine.executors.sprites.strategies.rotation.RotationStrategy;
import voogasalad.gameengine.executors.utils.SpriteArchetype;

import java.awt.geom.Point2D;
import java.util.List;

public class JavaFXSprite implements Sprite {
    private int mySpriteId;
    private Point2D.Double currentPosition;
    private double myCurrentAttackAngle;
    private HealthStrategy myHealthStrategy;
    private MovementStrategy myMovementStrategy;
    private RotationStrategy myRotationStrategy;
    private AttackStrategy myAttackStrategy;
    private CostStrategy myCostStrategy;
    private String myImagePath;
    private ImageView myImageView;
    private SpriteBuilder myOriginalBuilder;
    private SpriteArchetype myArchetype;
    private int myPrototypeId;

    public JavaFXSprite(SpriteBuilder builder) throws GameEngineException {
        myPrototypeId = builder.getPrototypeId();
        myArchetype = builder.getSpriteArchetype();
        myOriginalBuilder = builder;
        mySpriteId = builder.getSpriteId();
        currentPosition = new Point2D.Double();
        currentPosition.setLocation(builder.getX(), builder.getY());
        myHealthStrategy = builder.getHealthStrategy();
        myMovementStrategy = builder.getMovementStrategy();
        myAttackStrategy = builder.getAttackStrategy();
        myRotationStrategy = builder.getRotationStrategy();
        myCostStrategy = builder.getCostStrategy();
        myCurrentAttackAngle = 0.0;
        myImagePath = builder.getImagePath();
        configureImageView(builder.getHeight(), builder.getWidth());
    }

    @Override
    public Sprite makeClone(double x, double y, int spriteId) throws GameEngineException {
        return new SpriteBuilder().setSpriteId(spriteId).setX(x).setY(y)
                .setHeight(myOriginalBuilder.getHeight())
                .setImagePath(myOriginalBuilder.getImagePath())
                .setWidth(myOriginalBuilder.getWidth())
                .setArchetype(myOriginalBuilder.getSpriteArchetype())
                .setAttackStrategy(myOriginalBuilder.getAttackStrategy().makeClone())
                .setRotationStrategy(myOriginalBuilder.getRotationStrategy().makeClone())
                .setMovementStrategy(myOriginalBuilder.getMovementStrategy().makeClone())
                .setHealthStrategy(myOriginalBuilder.getHealthStrategy().makeClone())
                .setCostStrategy(myOriginalBuilder.getCostStrategy().makeClone())
                .setPrototypeId(myPrototypeId)
                .build();
    }

    @Override
    public void shoot(double elapsedTime, LevelActionsRequester levelActionsRequester) throws GameEngineException {
        myAttackStrategy.attack(elapsedTime, myCurrentAttackAngle, levelActionsRequester, currentPosition);
    }

    @Override
    public void updateShootingAngle(double elapsedTime){
        myCurrentAttackAngle = myRotationStrategy.updateAngle(elapsedTime, myCurrentAttackAngle);
    }

    @Override
    public void updatePath(List<Point2D.Double> path) {
        myMovementStrategy.updatePath(path);
    }

    @Override
    public void updateMovementAngle(double angle) {
        myMovementStrategy.updateDirectionalAngle(angle);
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

    @Override
    public String getImagePath(){
        return myImagePath;
    }

    @Override
    public int getPrototypeId() {
        return myPrototypeId;
    }

    @Override
    public int getHealth() {
        return myHealthStrategy.getHealth();
    }

    @Override
    public SpriteArchetype getSpriteArchetype() {
        return myArchetype;
    }

    @Override
    public void updatePosition(double elapsedTime) {
        currentPosition = myMovementStrategy.calculateNextPosition(elapsedTime, currentPosition);
    }

    private void configureImageView(double height, double width) {
        myImageView = new ImageView(new Image(this.getClass().getClassLoader().getResourceAsStream(myImagePath)));
        myImageView.setFitHeight(height);
        myImageView.setFitWidth(width);
        myImageView.setPreserveRatio(true);
    }

    @Override
    public boolean isMovementFinished(){
        return myMovementStrategy.isMovementFinished();
    }

    @Override
    public void chunkHealth(int damage) {
        myHealthStrategy.chunkHealth(damage);
    }

    @Override
    public boolean isDead() {
        return myHealthStrategy.getHealth() != null && myHealthStrategy.getHealth() <= 0;
    }

}
