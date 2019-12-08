package voogasalad.gameengine.executors.control.action.editprototype;

import org.w3c.dom.Element;
import voogasalad.gameengine.executors.sprites.Sprite;

import java.util.List;

public class EditPrototypeImageViewAction extends EditPrototypeAction {

    private String myImageViewPath;

    public EditPrototypeImageViewAction(Element editableObjectRoot) {
        super(editableObjectRoot);
        myImageViewPath = editableObjectRoot.getElementsByTagName(super.EDITED_FIELD_TAG).item(0).getTextContent();
    }


    protected void updateSprites(List<Sprite> spritesList) {
        for (Sprite sprite : spritesList) {
            if (sprite.getPrototypeId()==super.getPrototypeId()) {
                sprite.updateImage(myImageViewPath);
            }
        }
    }
}
