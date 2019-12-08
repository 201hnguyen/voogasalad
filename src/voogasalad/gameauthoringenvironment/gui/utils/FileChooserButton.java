package voogasalad.gameauthoringenvironment.gui.utils;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class FileChooserButton extends Button {

    private Stage newStage = new Stage();
    private ImageView imageView;
    private String selectedImage = "";

    public FileChooserButton(){
        super("Select Image");
        //this.imageView = getImageView();
        createButton();

    }

    /**
     *
     * @return
     */
    public ImageView getImageView() {
        return imageView;
    }

    //TODO: add exception
    private void createButton() {
        final FileChooser imageChooser = new FileChooser();
        this.setOnAction((final ActionEvent e) -> {
            File file = imageChooser.showOpenDialog(newStage);
            if (file != null) {
                Image image = new Image(file.toURI().toString());
                imageView = new ImageView(image);
                //imageView.setFitWidth(500);
                //imageView.setFitHeight(500);
            }
        });
    }

}
