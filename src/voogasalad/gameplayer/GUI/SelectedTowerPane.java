package voogasalad.gameplayer.GUI;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import voogasalad.gameengine.api.ActionsProcessor;
import voogasalad.gameengine.api.GameSceneObject;
import voogasalad.gameengine.executors.sprites.Sprite;

public class SelectedTowerPane extends VBox {

    private DisplayScreen displayScreen;
    private ActionsProcessor actionsProcessor;

    public SelectedTowerPane(ActionsProcessor actionsProcessor, DisplayScreen display) {
        setMinWidth(200);
        setMinHeight(400);
        this.actionsProcessor = actionsProcessor;
        this.displayScreen = display;
        this.setBackground(new Background(new BackgroundFill(Color.ALICEBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
    }

    public void removeTower(Sprite sprite, int x, int y) {
        HBox removeTowerBox = new HBox();

        ImageView towerImage = new ImageView(new Image(sprite.getImagePath()));
        Label removeTowerButton = new Label("Remove Tower");
        removeTowerButton.setOnMouseClicked(e -> {
            actionsProcessor.processSellTowerAction(x,y);
            this.getChildren().remove(removeTowerBox);
//            GameSceneObject gso = actionsProcessor.processExecuteNoElapsedTimeSceneAction();
//            displayScreen.updateDisplayScreen(gso.getOnScreenSprites());
        });
        removeTowerButton.setOnMouseEntered(e -> removeTowerButton.setBackground(new Background(new BackgroundFill(Color.LIMEGREEN, CornerRadii.EMPTY, Insets.EMPTY))));
        removeTowerButton.setOnMouseExited(e -> removeTowerButton.setBackground(new Background(new BackgroundFill(Color.GHOSTWHITE, CornerRadii.EMPTY, Insets.EMPTY))));
        removeTowerBox.getChildren().addAll(towerImage, removeTowerButton);
        removeTowerBox.setMinHeight(200);
        removeTowerBox.setMinWidth(200);
        this.getChildren().add(removeTowerBox);
    }

}
