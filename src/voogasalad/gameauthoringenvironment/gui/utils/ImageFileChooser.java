package voogasalad.gameauthoringenvironment.gui.utils;

import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.function.Consumer;

public class ImageFileChooser {

    private Button myButton;
    //private HBox root;
    private Stage currentStage;
    private File selectedFile;

    public ImageFileChooser() {
        myButton = createFileChooserButton();

    }

    //handler for a button takes a method
    private Button createFileChooserButton() {

        //make button
        myButton = new Button("Upload A File");


        //declare a file chooser
        FileChooser fileChooser = new FileChooser();
        selectedFile = fileChooser.showOpenDialog(currentStage);

        //connect file chooser to a button
        myButton.setOnAction();

        return myButton;

    }

    private void setButtonAction() {

        myButton.setOnAction();

    }

}
