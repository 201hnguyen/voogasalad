package gae_gui;

import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;

public class TestButton extends Button {

    public TestButton(){
        super("TEST");
        setOnMouseClicked(event -> {
            System.out.println("TEST");
        });
    }
}
