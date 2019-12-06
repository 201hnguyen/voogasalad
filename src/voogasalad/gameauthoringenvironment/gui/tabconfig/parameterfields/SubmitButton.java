package voogasalad.gameauthoringenvironment.gui.tabconfig.parameterfields;


import javafx.scene.Node;
import javafx.scene.control.Button;
import voogasalad.gameauthoringenvironment.gui.AddToXML;
import voogasalad.gameauthoringenvironment.gui.SaveGUIParameters;

import java.util.List;

public class SubmitButton extends Button {
    public SubmitButton(){}

    public SubmitButton(ParameterCreator parameterCreatorInstanceParam){
        super("CREATE");
        this.setOnMouseClicked(event -> {
            parameterCreatorInstanceParam.createSubmitButton();
        });
    }

}
