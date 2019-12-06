package voogasalad.gameauthoringenvironment.gui.tabconfig.parameterfields;

import javafx.geometry.Insets;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 * A class to display an image selected by the user
 */
public class ImagePreviewCreator {

    private VBox previewVBox;
    private String mySelectedImage;
    private FieldTextReturnFactory fieldFactory = new FieldTextReturnFactory();

    public ImagePreviewCreator() {
       // mySelectedImage = InputFieldCreator.getMySelectedImage();
        previewVBox = createPreviewVBox(mySelectedImage);
    }

    //a helper method to assemble the image preview VBox given the name of an image
    //TODO: display image
    private VBox createPreviewVBox(String imageName) {
        previewVBox = new VBox();
        Label label = new Label("Preview");
        previewVBox.setPrefWidth(150);
        previewVBox.setPadding(new Insets(0, 20, 10, 20));
        previewVBox.getChildren().addAll(label);

        return previewVBox;
    }

}
