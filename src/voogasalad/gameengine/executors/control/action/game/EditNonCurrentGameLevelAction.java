package voogasalad.gameengine.executors.control.action.game;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import voogasalad.gameengine.configurators.LevelConfigurator;
import voogasalad.gameengine.executors.control.gamecontrol.Game;
import voogasalad.gameengine.executors.control.levelcontrol.Level;
import voogasalad.gameengine.executors.exceptions.GameEngineException;

import java.util.ArrayList;
import java.util.List;
/**
 * Class:
 * Purpose:
 * Assumptions:
 * Dependencies:
 * Example of how to use:
 * Other details:
 */
public class EditNonCurrentGameLevelAction implements GameAction {

    private NodeList myEditedLevelsList;

    /**
     * Purpose:
     * Assumptions:
     * @param editableObjectRoot
     */
    public EditNonCurrentGameLevelAction(Element editableObjectRoot) {
        myEditedLevelsList = editableObjectRoot.getElementsByTagName("Level");
    }

    /**
     * Purpose:
     * Assumptions:
     * @param game
     * @throws GameEngineException
     */
    @Override
    public void execute(Game game) throws GameEngineException {
        LevelConfigurator levelConfigurator = new LevelConfigurator();
        List<Level> editedLevels = levelConfigurator.configureLevels(myEditedLevelsList, game.getCompletePrototypesCollection());
        List<Level> currentGameLevels = game.getLevels();
        List<Level> originalLevelsToRemove = new ArrayList<>();
        for (Level newLevel : editedLevels) {
            for (Level oldLevel : currentGameLevels) {
                if (newLevel.getLevelId() == oldLevel.getLevelId()) {
                    originalLevelsToRemove.add(oldLevel);
                }
            }
        }
        currentGameLevels.removeAll(originalLevelsToRemove);
        currentGameLevels.addAll(editedLevels);
    }

    /**
     * Purpose:
     * Assumptions:
     * @return
     */
    @Override
    public boolean isFinished() {
        return true;
    }
}
