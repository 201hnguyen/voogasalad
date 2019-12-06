package voogasalad.gameauthoringenvironment.gui.tabconfig.parameterfields;

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;

import java.io.File;

/**
 * A class to display an image selected by the user
 */
public class ImagePreviewCreator {

    private VBox previewVBox;
    private String mySelectedImage ="";
    private FieldTextReturnFactory fieldFactory = new FieldTextReturnFactory();

    public ImagePreviewCreator() {
       // mySelectedImage = InputFieldCreator.getMySelectedImage();
        //previewVBox = createPreviewVBox(mySelectedImage);
        previewVBox = createPreviewVBox("bird.png");
    }

    /**
     * A method to get a VBox that displays the image selected by the designer
     * @return
     */
    public VBox getPreviewVBox() { return previewVBox; }

        //from MapConfig
//    private void addSelectBackgroundButton(){
//        final FileChooser imageChooser = new FileChooser();
//        Button newButton = new Button("Select Background Image");
//        newButton.setLayoutX();
//        newButton.setLayoutY(yPosition);
//        yPosition+=spacing;
//        newButton.setId("NewRoute");
//        newButton.setOnAction(e ->createNewRoute());
//        newButton.setOnAction((final ActionEvent e) -> {
//            File file = imageChooser.showOpenDialog(levelConfigPage);
//            if (file != null) {
//                Image image1 = new Image(file.toURI().toString());
//                ImageView ip = new ImageView(image1);
//                ip.setFitWidth(500);
//                ip.setFitHeight(500);
//                subRoot.getChildren().add(ip); //subRoot is a group
//            }
//        });
//
//        //root.getChildren().add(newButton);
//        buttonsvbox.getChildren().add(newButton);
//    }

    //a helper method to assemble the image preview VBox given the name of an image
    //TODO: display image
    private VBox createPreviewVBox(String imageName) {
        previewVBox = new VBox();
        Label header = new Label("Preview");
        header.setFont(Font.font(14));
        previewVBox.setPrefWidth(300);
        previewVBox.setPadding(new Insets(50, 50, 50, 50));


        //get resource bundle of files


        //match imageName to option in resource bundle


        Image testImage = new Image(imageName);
        ImageView testView = new ImageView(testImage);
        testView.setFitHeight(200);
        testView.setFitWidth(200);

        previewVBox.getChildren().addAll(
                header,
                testView);

        return previewVBox;
    }

}
