package voogasalad.gameauthoringenvironment.gui.tabconfig;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class FileChooserButton extends Button {
    private Stage newStage = new Stage();

    public FileChooserButton(){
        super("Select Background Image");
        final FileChooser imageChooser = new FileChooser();
        this.setOnAction((final ActionEvent e) -> {
            File file = imageChooser.showOpenDialog(newStage);
            if (file != null) {
                Image image1 = new Image(file.toURI().toString());
                ImageView ip = new ImageView(image1);
                ip.setFitWidth(500);
                ip.setFitHeight(500);
            }
        });
    }

}
