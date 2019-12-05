package voogasalad.gameplayer.GUI;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.Map;

public class StatusBar extends VBox {

    private static final int INFO_BOX_SPACING = 10;
    private static final int ICON_SIZE = 50;

    private HBox infoTrackerBox(String infoName, int infoValue) {
        // need to add icon and value
        HBox infoBox = new HBox(INFO_BOX_SPACING);
        ImageView icon = iconLoader(infoName);
        Text value = new Text();
        value.setText(String.valueOf(infoValue));
        infoBox.getChildren().add(icon);
        infoBox.getChildren().add(value);
        return infoBox;
    }

    public void updateDisplayedInfo(Map<String, Integer> gameInfoMap) {
        this.getChildren().clear();
        for(String key : gameInfoMap.keySet()) {
            HBox box = infoTrackerBox(key, gameInfoMap.get(key));
            this.getChildren().add(box);
        }
    }

    /**
     *
     * @param iconIdentifier - key from the gameInfoMap such as Lives or Coins
     * @return image view of the icon associated with the key
     */
    private ImageView iconLoader(String iconIdentifier) {
        iconIdentifier = iconIdentifier.toLowerCase();
        ImageView icon = new ImageView(new Image(iconIdentifier + ".png"));
        icon.setFitHeight(ICON_SIZE);
        icon.setFitWidth(ICON_SIZE);
        return icon;
    }
}
