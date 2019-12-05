package voogasalad.everything_gae.gae_gui.level_map_config.level_config.nodes;

import javafx.scene.control.Button;
import org.w3c.dom.Document;
import voogasalad.everything_gae.bus.Bus;
import voogasalad.everything_gae.gae_gui.AddToXML;
import voogasalad.gameengine.executors.exceptions.GameEngineException;

import javax.xml.parsers.ParserConfigurationException;

public class SubmitButton extends Button {
    private AddToXML sendToXML;
    public Document createdXML;
    public Bus busInstance;

    public SubmitButton(Document createdXMLParam, AddToXML sendToXMLParam, Bus busInstanceParam){
        super("Submit");
        createdXML = createdXMLParam;
        sendToXML = sendToXMLParam;
        busInstance = busInstanceParam;
        this.setOnMouseClicked(event -> {
            try {
                createdXML = sendToXML.createXML();
                busInstance.goToPlayer(createdXML);

            } catch (ParserConfigurationException | GameEngineException e) {
                e.printStackTrace();
            }
        });
    }
}
