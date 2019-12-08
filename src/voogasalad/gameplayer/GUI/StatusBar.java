package voogasalad.gameplayer.GUI;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

public class StatusBar extends HBox {

    private static final String RESOURCE_PATH = "resources.player.PlayerViewOptions";
    private static final ResourceBundle resourceBundle = ResourceBundle.getBundle(RESOURCE_PATH);
    private static final int INFO_BOX_SPACING = Integer.parseInt(resourceBundle.getString("InfoBoxSpacing"));
    private static final int ICON_SIZE = Integer.parseInt(resourceBundle.getString("StatusBarIconSize"));
    private static final int STATUS_BAR_FONT_SIZE = Integer.parseInt(resourceBundle.getString("StatusBarFontSize"));

    private List<HBox> currentDisplayedInfo;

    public StatusBar(){
        super(INFO_BOX_SPACING);
        currentDisplayedInfo = new ArrayList<>();
    }

    private HBox infoTrackerBox(String infoName, int infoValue) {
        // need to add icon and value
        HBox infoBox = new HBox(INFO_BOX_SPACING);
        ImageView icon = iconLoader(infoName);
        Text value = new Text();
        value.setText("\n" + infoValue);
        value.setFont(new Font(STATUS_BAR_FONT_SIZE));
        infoBox.getChildren().add(icon);
        infoBox.getChildren().add(value);
        currentDisplayedInfo.add(infoBox);
        return infoBox;
    }

    public void updateDisplayedInfo(Map<String, Integer> gameInfoMap) {
        this.getChildren().removeAll(currentDisplayedInfo);
        currentDisplayedInfo.clear();
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
