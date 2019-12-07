package voogasalad.gameplayer.GUI;

import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import voogasalad.gameengine.api.ActionsProcessor;

public class SelectedTowerPane extends VBox {

    private ActionsProcessor actionsProcessor;

    public SelectedTowerPane(ActionsProcessor actionsProcessor) {
        setMinWidth(200);
        setMinHeight(400);
        this.actionsProcessor = actionsProcessor;
    }

    private HBox removeTower(ImageView towerImage) {
        HBox removeTowerBox = new HBox();
        Label removeTowerButton = new Label("Remove Tower");
        removeTowerButton.setOnMouseClicked(e -> {
            actionsProcessor.processSellTowerAction(e.getX(), e.getY());
        });
        removeTowerBox.getChildren().addAll(towerImage, removeTowerButton);
        return removeTowerBox;
    }

}
