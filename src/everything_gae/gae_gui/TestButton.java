package everything_gae.gae_gui;

import javafx.scene.control.Button;

public class TestButton extends Button {

    public TestButton(){
        super("TEST");
        setOnMouseClicked(event -> {
            System.out.println("TEST");
        });
    }
}
