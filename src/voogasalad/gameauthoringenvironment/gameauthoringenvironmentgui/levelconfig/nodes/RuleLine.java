package voogasalad.gameauthoringenvironment.gameauthoringenvironmentgui.levelconfig.nodes;

import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class RuleLine extends HBox {

    public RuleLine(){
        super(10);
        this.getChildren().addAll(new Label("Condition: "), new ComboBox(), new Label("  --->   Action: "), new ComboBox());
    }

}
