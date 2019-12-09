package voogasalad.gameauthoringenvironment.gui.mapconfig;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.ResourceBundle;
import java.util.concurrent.Flow;

public class GameInfoConfig extends BorderPane {
    private VBox mainVBox;
    private VBox titleTextVBox;
    private HBox inputHBox;
    private HBox buttonHBox;
    private BorderPane titleBorderPane;
    private String gameInfoResourcePath = "resources.gae.GameInfoFields";
    private ResourceBundle gameInfoRB;
    private static final String textFieldLabel = "textFieldLabel";
    private static final String labelText = "labelText";
    public GameInfoConfig() {
        mainVBox = new VBox(20);
        gameInfoRB = ResourceBundle.getBundle(gameInfoResourcePath);
        titleTextVBox = new VBox(10);
        titleBorderPane = new BorderPane();
        buttonHBox = new HBox();
        //titleBorderPane.setCenter(titleTextVBox);
        inputHBox = new HBox(10);
        mainVBox.getChildren().add(titleBorderPane);
        mainVBox.getChildren().add(inputHBox);
        addTitle();
        addFieldLabel();
        addInputField();
        this.setCenter(mainVBox);
    }

    private void addTitle() {


        Label title = new Label("Make A New Game");
        title.setFont(new Font("Arial", 40));

        titleBorderPane.setCenter(title);
        //titleTextVBox.getChildren().add(title);


    }

    private void addFieldLabel(){
        VBox textLabelVBox = new VBox(15);
        String fieldNames [] = gameInfoRB.getString(textFieldLabel).split(",");
        for (String fieldName: fieldNames){
            Label fieldLabel = new Label(fieldName);

            TextField inputField = new TextField();
            if (fieldName.equals("Game Description")) {
                FlowPane inputFieldFlowPane = new FlowPane(inputField);
                inputFieldFlowPane.setMinWidth(200);
                inputFieldFlowPane.setMinHeight(200);

            }


            textLabelVBox.getChildren().add(fieldLabel);
            //textFieldVBox.getChildren().add(inputField);

        }

        inputHBox.getChildren().add(textLabelVBox);


    }

    private void addInputField() {
        VBox inputFieldVBox = new VBox(10);
        TextField gameName = new TextField("Enter The Name Of The Game Here");
        TextField gameAuthor = new TextField("Enter The List of Authors");
        TextField gameDescription = new TextField("Describe Your Game");
        //gameDescription.setPrefHeight(150);
        //gameDescription.setPrefWidth(150);
        FlowPane newFlowPane = new FlowPane();
        newFlowPane.getChildren().add(gameDescription);
        ScrollPane descriptionScrollPane = new ScrollPane();
        descriptionScrollPane.setPrefHeight(150);
        descriptionScrollPane.setPrefWidth(150);

        descriptionScrollPane.setContent(newFlowPane);
        inputFieldVBox.getChildren().add(gameName);
        inputFieldVBox.getChildren().add(gameAuthor);
        inputFieldVBox.getChildren().add(descriptionScrollPane);

        inputHBox.getChildren().add(inputFieldVBox);

    }

    private void addButtons(){

    }
}
