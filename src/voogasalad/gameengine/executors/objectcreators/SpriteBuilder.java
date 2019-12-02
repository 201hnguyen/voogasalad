package voogasalad.gameengine.executors.objectcreators;

import voogasalad.gameengine.executors.exceptions.GameEngineException;
import voogasalad.gameengine.executors.sprites.Sprite;
import voogasalad.gameengine.executors.utils.SpriteArchetype;
import voogasalad.gameengine.executors.sprites.strategies.health.HealthStrategy;
import voogasalad.gameengine.executors.sprites.strategies.movement.MovementStrategy;

import java.util.HashMap;

public class SpriteBuilder {

    private int mySpriteId;
    private double myWidth;
    private double myHeight;
    private double myXPos;
    public double myYPos;
    private HealthStrategy myHealthStrategy;
    private MovementStrategy myMovementStrategy;
    private String myImagePath;
    private SpriteArchetype myArchetype;
    private int myPrototypeId;

    public SpriteBuilder setX(double xPos) {
        myXPos = xPos;
        return this;
    }

    public double getX() {
        return myXPos;
    }

    public SpriteBuilder setPrototypeId(int prototypeId) {
        myPrototypeId = prototypeId;
        return this;
    }

    public SpriteBuilder setPrototypeId(String prototypeId) {
        Integer id = Integer.parseInt(prototypeId);
        myPrototypeId = id;
        System.out.println("prototype id set in builder with value: " + myPrototypeId);
        return this;
    }

    public int getPrototypeId() {
        return myPrototypeId;
    }

    public SpriteBuilder setY(double yPos) {
        myYPos = yPos;
        return this;
    }

    public double getY() {
        return myYPos;
    }

    public SpriteBuilder setSpriteId(int id) {
        mySpriteId = id;
        return this;
    }

    public int getSpriteId() {
        return mySpriteId;
    }

    public SpriteBuilder setHealthStrategy(HealthStrategy healthStrategy) {
        myHealthStrategy = healthStrategy;
        return this;
    }

    public HealthStrategy getHealthStrategy() {
        return myHealthStrategy;
    }

    public SpriteBuilder setMovementStrategy(MovementStrategy movementStrategy) {
        myMovementStrategy = movementStrategy;
        return this;
    }

    public MovementStrategy getMovementStrategy() {
        return myMovementStrategy;
    }

    public SpriteBuilder setImagePath(String imagePath) {
        myImagePath = imagePath;
        System.out.println("image path set in builder with value: " + myImagePath);
        return this;
    }

    public String getImagePath() {
        return myImagePath;
    }

    public SpriteBuilder setWidth(double width) {
        myWidth = width;
        return this;
    }

    public SpriteBuilder setWidth(String width) {
        Double widthDouble = Double.parseDouble(width);
        myWidth = widthDouble;
        System.out.println("width set in builder with value: " + myWidth);
        return this;
    }

    public double getWidth() {
        return myWidth;
    }

    public SpriteBuilder setHeight(double height) {
        myHeight = height;
        return this;
    }

    public SpriteBuilder setHeight(String height) {
        Double heightDouble = Double.parseDouble(height);
        myHeight = heightDouble;
        System.out.println("height set in builder with value: " + myHeight);
        return this;
    }

    public double getHeight() {
        return myHeight;
    }

    public SpriteArchetype getSpriteArchetype() {
        return myArchetype;
    }

    public SpriteBuilder setArchetype(SpriteArchetype archetype) {
        myArchetype = archetype;
        return this;
    }

    public SpriteBuilder setArchetype(String archetypeString) {
        SpriteArchetype archetype = SpriteArchetype.valueOf(archetypeString);
        myArchetype = archetype;
        System.out.println("archetype set in builder with value: " + myArchetype);
        return this;
    }

    public Sprite build() throws GameEngineException {
        checkParametersAndAssignDefault();
        SpriteProductsFactory spriteProductsFactory = new SpriteProductsFactory();
        Sprite sprite = spriteProductsFactory.makeSprite(this);
        return sprite;
    }

    private void checkParametersAndAssignDefault() throws GameEngineException {
        StrategiesFactory strategiesFactory = new StrategiesFactory();
        if (myMovementStrategy == null) myMovementStrategy = strategiesFactory.makeMovement("NoMovement", new HashMap<>());
        if (myHealthStrategy == null) myHealthStrategy = strategiesFactory.makeHealth("NoHealth", new HashMap<>());
        if (myImagePath == null) myImagePath = "pandaslogo.png";
        if (myArchetype == null) myArchetype = SpriteArchetype.UNCLASSIFIED;
    }
}





