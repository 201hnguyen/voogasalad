package voogasalad.gameengine.executors.control.action.game;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import voogasalad.gameengine.configurators.LevelConfigurator;
import voogasalad.gameengine.executors.control.gamecontrol.Game;
import voogasalad.gameengine.executors.control.levelcontrol.Level;
import voogasalad.gameengine.executors.exceptions.GameEngineException;

import java.util.ArrayList;
import java.util.List;

public class EditNonCurrentGameLevelAction implements GameAction {

    private NodeList myEditedLevelsList;

    public EditNonCurrentGameLevelAction(Element editableObjectRoot) {
        myEditedLevelsList = editableObjectRoot.getElementsByTagName("Level");
    }

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

    @Override
    public boolean isFinished() {
        return true;
    }
}
