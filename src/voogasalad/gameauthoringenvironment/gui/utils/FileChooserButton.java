package voogasalad.gameauthoringenvironment.gui.utils;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.util.Enumeration;
import java.util.Properties;
import java.util.ResourceBundle;

public class FileChooserButton extends Button {

    private static Stage newStage = new Stage();
    private String imageString;


    public FileChooserButton() {
        super("Select Image");
        createButton();
        this.getImageString();
    }

    /**
     *
     */
    public String getImageString() {
        return imageString;
    }

    //TODO: add exception
    private void createButton() {
        final FileChooser imageChooser = new FileChooser();
        this.setOnAction((final ActionEvent e) -> {
            File file = imageChooser.showOpenDialog(newStage);
            imageString = file.toURI().toString();
            System.out.println("FileChooserButton " + imageString);
        });
    }
}

