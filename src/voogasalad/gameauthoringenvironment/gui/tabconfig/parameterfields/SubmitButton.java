package voogasalad.gameauthoringenvironment.gui.tabconfig.parameterfields;


import javafx.scene.Node;
import javafx.scene.control.Button;
import voogasalad.gameauthoringenvironment.gui.AddToXML;
import voogasalad.gameauthoringenvironment.gui.SaveGUIParameters;

import java.util.List;

public class SubmitButton extends Button {
    private ParameterCreator parameterCreatorInstance;
    public SubmitButton(){}

    public SubmitButton(ParameterCreator parameterCreatorInstanceParam){
        super("CREATE");
        this.setOnMouseClicked(event -> {
            parameterCreatorInstanceParam.createSubmitButton();
        });
    }

//    public SubmitButton(List<Node> allNodes, List<String> labelValue, FieldTextReturnFactory fieldFactory, List<String> labelText, AddToXML xmlObject, String gameObjectName){
//        super("CREATE");
//        this.setOnMouseClicked(event -> {
//            allNodes
//                    .stream()
//                    .forEach(node -> labelValue.add(fieldFactory.getAppropriateText(node)));
//
//            SaveGUIParameters myGuiParameters = new SaveGUIParameters(labelText, labelValue);
//            String myLabel = xmlObject.addToSendToXMLMap(myGuiParameters.getMap(), gameObjectName);
//        });
//
//    }

}
