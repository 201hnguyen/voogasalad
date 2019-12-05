package voogasalad.gameplayer.GUI;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.Map;
import java.util.Set;

public class StatusBar extends HBox {

    private HBox infoTrackerBox(String infoName, int infoValue) {
        // need to add icon and value
        HBox infoBox = new HBox(5);
        ImageView icon = iconLoader(infoName);
        Text value = new Text();
        value.setText("\n" + String.valueOf(infoValue));
        value.setFont(new Font(20));
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
        icon.setFitHeight(50);
        icon.setFitWidth(50);
        return icon;
    }
}
