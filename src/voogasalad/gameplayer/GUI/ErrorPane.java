package voogasalad.gameplayer.GUI;


import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import static javax.swing.text.StyleConstants.setBackground;

public class ErrorPane extends Pane {

    private Alert errorPopUp;

    public ErrorPane(){
        setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        errorPopUp = new Alert(Alert.AlertType.INFORMATION);
        errorPopUp.setTitle("ERROR");
    }

    /**
     * This method displays a pop up message when an error occurs due to the user inputting invalid commands.
     * @param err: the message to display when an error occurs
     */
    public void errorMessage(String err){
        Text error = new Text(err);
        errorPopUp.setContentText(err);
        errorPopUp.showAndWait();
        getChildren().add(error);
    }
}
