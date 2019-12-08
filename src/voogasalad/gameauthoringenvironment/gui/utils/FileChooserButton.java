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

   // private static final String PROPERTIES_PATH = "resources.gae.tabcreation";
   // private static final String SELECTED_IMAGE_RESOURCE = "resources.gae.tabcreation.CurrentSelectedImage";
    private static Stage newStage = new Stage();
    private String imageString;
   // private Properties imageProp;
   // private ResourceBundle imageResource;



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
        //imageResource = ResourceBundle.getBundle(SELECTED_IMAGE_RESOURCE);

        final FileChooser imageChooser = new FileChooser();
        this.setOnAction((final ActionEvent e) -> {
            File file = imageChooser.showOpenDialog(newStage);
            imageString = file.toURI().toString();
            System.out.println("FileChooserButton " + imageString);

            //imageResource.

            //createPropertiesFile();
//            if (file != null) {
//                Image image = new Image(selectedImage);
//                imageView = new ImageView(image);
//                imageView.setFitWidth(500);
//                imageView.setFitHeight(500);
//           }
        });
    }

    private void clearPropertiesFile() {

    }


//    private void createPropertiesFile() {
//        try (OutputStream writer = new FileOutputStream(PROPERTIES_PATH)) {
//
//            imageProp = new Properties();
//
//            clearPropertiesFile();
//            //clearPropertiesFile(writer);
//
//            // set the properties value
//            imageProp.setProperty("image.url", imageString);
//
//            // save properties to project root folder
//            imageProp.store(writer, null);
//
//            System.out.println(imageProp);
//
//        } catch (IOException io) {
//            io.printStackTrace();
//        }
//    }
//
//

//    private void clearPropertiesFile(OutputStream writer) throws IOException {
//        InputStream reader = new FileInputStream(PROPERTIES_PATH);
//        imageProp.load(reader);
//        imageProp.remove("image.url");
//        imageProp.store(writer, null);
//    }
}



