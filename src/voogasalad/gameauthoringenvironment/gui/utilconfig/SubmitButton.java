package voogasalad.gameauthoringenvironment.gui.utilconfig;


import javafx.scene.control.Button;
import voogasalad.gameauthoringenvironment.gui.tabconfig.parameterfields.ParameterCreator;

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
