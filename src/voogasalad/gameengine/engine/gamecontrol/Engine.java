package voogasalad.gameengine.engine.gamecontrol;

import org.w3c.dom.Document;
import voogasalad.gameengine.api.GameSceneObject;
import voogasalad.gameengine.engine.exceptions.GameEngineException;
import voogasalad.gameengine.engine.sprites.Sprite;
import voogasalad.gameengine.engineconfig.EngineConfigurator;

import java.util.List;

public class Engine {

    private EngineConfigurator myEngineConfigurator;
    private Level myCurrentLevel;

    public Engine() {
        myEngineConfigurator = new EngineConfigurator();
    }

    public void configureGame(Document doc) throws GameEngineException {
        myEngineConfigurator.loadXML(doc);
        myEngineConfigurator.initializeGame();
        myCurrentLevel = myEngineConfigurator.initializeEngineForGame();
    }

    public Level getCurrentLevel() {
        return myCurrentLevel;
    }

    public GameSceneObject execute(double elapsedTime) throws GameEngineException {
        return myCurrentLevel.execute(elapsedTime);
    }

    public List<Sprite> getSpritePrototypes() {
        return myCurrentLevel.getSpriteManager().getSpritePrototypes();
    }
}
