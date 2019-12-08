package voogasalad.gameengine.executors.control.action.editprototype;

import org.w3c.dom.Element;
import voogasalad.gameengine.configurators.PrototypesConfigurator;
import voogasalad.gameengine.executors.exceptions.GameEngineException;
import voogasalad.gameengine.executors.sprites.Sprite;
import voogasalad.gameengine.executors.sprites.strategies.attack.AttackStrategy;

import java.util.List;

public class EditPrototypeAttackStrategyAction extends EditPrototypeAction {

    public static final String ATTACK_STRATEGY_TAG = "AttackStrategy";

    private AttackStrategy myAttackStrategy;

    public EditPrototypeAttackStrategyAction(Element editableObjectRoot) throws GameEngineException {
        super(editableObjectRoot);
        Element editedField = (Element) editableObjectRoot.getElementsByTagName(super.EDITED_FIELD_TAG).item(0);
        Element attackStrategyRoot = (Element) editedField.getElementsByTagName(ATTACK_STRATEGY_TAG).item(0);
        PrototypesConfigurator configurator = new PrototypesConfigurator();
        myAttackStrategy = (AttackStrategy) configurator.buildStrategy(attackStrategyRoot);
    }

    protected void updateSprites(List<Sprite> sprites) throws GameEngineException {
        for (Sprite sprite : sprites) {
            if (sprite.getPrototypeId() == super.getPrototypeId()) {
                sprite.updateAttackStrategy(myAttackStrategy.makeClone());
            }
        }
    }
}
