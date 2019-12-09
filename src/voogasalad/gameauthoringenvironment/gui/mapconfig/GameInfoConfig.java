package voogasalad.gameauthoringenvironment.gui.mapconfig;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.ResourceBundle;
import java.util.concurrent.Flow;

public class GameInfoConfig extends BorderPane {
    private VBox mainVBox;
    private VBox titleTextVBox;
    private HBox inputHBox;
    private HBox buttonHBox;
    private BorderPane titleBorderPane;
    private BorderPane authorsBorderPane;

    private Label title;
    private Label authors;
    private String gameInfoResourcePath = "resources.gae.GameInfoFields";
    private ResourceBundle gameInfoRB;
    private static final String textFieldLabel = "textFieldLabel";
    private static final String labelText = "labelText";
    public GameInfoConfig() {
        mainVBox = new VBox(20);
        gameInfoRB = ResourceBundle.getBundle(gameInfoResourcePath);
        titleTextVBox = new VBox(10);
        titleBorderPane = new BorderPane();
        authorsBorderPane = new BorderPane();
        buttonHBox = new HBox();
        //titleBorderPane.setCenter(titleTextVBox);
        inputHBox = new HBox(10);
        mainVBox.getChildren().add(titleBorderPane);
        mainVBox.getChildren().add(authorsBorderPane);
        mainVBox.getChildren().add(inputHBox);
        addTitle();
        addFieldLabel();
        addInputField();
        addButtons();
        this.setCenter(mainVBox);
    }

    private void addTitle() {


        title = new Label("Make A New Game");
        title.setFont(new Font("Arial", 40));
        authors = new Label("List of Authors");
        authors.setFont(new Font("Arial", 25));

        titleBorderPane.setCenter(title);
        authorsBorderPane.setCenter(authors);
        //titleTextVBox.getChildren().add(title);


    }

    private void addFieldLabel(){
        VBox textLabelVBox = new VBox(15);
        String fieldNames [] = gameInfoRB.getString(textFieldLabel).split(",");
        for (String fieldName: fieldNames){
            Label fieldLabel = new Label(fieldName);
            textLabelVBox.getChildren().add(fieldLabel);
            //textFieldVBox.getChildren().add(inputField);

        }

        inputHBox.getChildren().add(textLabelVBox);


    }

    private void addInputField() {
        VBox inputFieldVBox = new VBox(10);
        TextField gameName = new TextField("Enter The Name Of The Game Here");
        gameName.setOnAction(e -> title.setText(gameName.getText()));
        TextField gameAuthor = new TextField("Enter The List of Authors");
        gameAuthor.setOnAction(e -> authors.setText(gameAuthor.getText()));

        TextArea gameDescription = new TextArea("Describe Your Game");
        gameDescription.setPrefHeight(150);
        gameDescription.setPrefWidth(150);
        //gameDescription.setPrefHeight(150);
        //gameDescription.setPrefWidth(150);


        ScrollPane descriptionScrollPane = new ScrollPane();
        descriptionScrollPane.setPrefHeight(150);
        descriptionScrollPane.setPrefWidth(150);

        //descriptionScrollPane.setContent(newFlowPane);
        inputFieldVBox.getChildren().add(gameName);
        inputFieldVBox.getChildren().add(gameAuthor);
        inputFieldVBox.getChildren().add(gameDescription);

        inputHBox.getChildren().add(inputFieldVBox);

    }

    private void addButtons(){
        VBox imageHBox = new VBox(20);

        Button uploadIconButton = new Button("Upload Game Icon");
        imageHBox.getChildren().add(uploadIconButton);

        final FileChooser imageChooser = new FileChooser();
        uploadIconButton.setOnAction((final ActionEvent e) -> {
            File file = imageChooser.showOpenDialog(new Stage());
            if (file != null) {
                Image image1 = new Image(file.toURI().toString());
                ImageView gameIcon = new ImageView(image1);
                gameIcon.setFitHeight(150);
                gameIcon.setFitWidth(150);
                imageHBox.getChildren().add(gameIcon);
            }
        });
        inputHBox.getChildren().add(imageHBox);

    }
}
