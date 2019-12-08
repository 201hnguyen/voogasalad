package voogasalad.gameauthoringenvironment.gui.utils;


import javafx.scene.Node;
import javafx.scene.control.Button;
import voogasalad.gameauthoringenvironment.gui.AddToXML;
import voogasalad.gameauthoringenvironment.gui.SaveGUIParameters;
import voogasalad.gameauthoringenvironment.gui.tabconfig.parameterfields.ParameterCreator;

import java.util.List;

public class SubmitButton extends Button {
    public SubmitButton(){}

    public SubmitButton(ParameterCreator parameterCreatorInstanceParam){
        super("CREATE");
        this.setOnMouseClicked(event -> {
            parameterCreatorInstanceParam.createSubmitButton();
            parameterCreatorInstanceParam.clearFields();
        });
    }

}
