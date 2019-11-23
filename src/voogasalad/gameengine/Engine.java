package voogasalad.gameengine;

import org.w3c.dom.Document;
import voogasalad.gameengine.api.GameSceneObject;
import voogasalad.gameengine.api.UIActionsProcessor;
import voogasalad.gameengine.executors.exceptions.GameEngineException;
import voogasalad.gameengine.executors.gamecontrol.Level;
import voogasalad.gameengine.executors.sprites.Sprite;
import voogasalad.gameengine.configurators.EngineConfigurator;
import voogasalad.gameengine.executors.utils.SpriteArchetype;

import java.util.List;
import java.util.Map;

public class Engine {

    private EngineConfigurator myEngineConfigurator;
    private Level myCurrentLevel;
    private UIActionsProcessor myCurrentUIActionsProcessor;

    public Engine(Document doc) throws GameEngineException {
        myEngineConfigurator = new EngineConfigurator();
        myEngineConfigurator.loadXML(doc);
        myEngineConfigurator.initializeGame();
        myCurrentLevel = myEngineConfigurator.initializeEngineForGame();
        myCurrentUIActionsProcessor = new UIActionsProcessor(myCurrentLevel);
    }

    public GameSceneObject execute(double elapsedTime) throws GameEngineException {
        return myCurrentLevel.execute(elapsedTime);
    }

    public UIActionsProcessor getUIActionProcessor() {
        return myCurrentUIActionsProcessor;
    }

    public List<Sprite> getSpritePrototypes() {
        return myCurrentLevel.getSpriteManager().getSpritePrototypes();
    }

    public Map<Integer, Sprite> getSpritePrototypesByArchetype(SpriteArchetype spriteArchetype) throws GameEngineException {
        return myCurrentLevel.getSpriteManager().getPrototypesForArchetype(spriteArchetype);
    }
}
