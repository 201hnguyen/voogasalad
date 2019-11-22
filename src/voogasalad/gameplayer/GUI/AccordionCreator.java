package voogasalad.gameplayer.GUI;
import javafx.scene.control.Accordion;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import voogasalad.gameengine.executors.sprites.Sprite;

        import java.util.List;

public class AccordionCreator extends Accordion {
    public static final int ITEM_HEIGHT = 50;
    public static final int ITEM_WIDTH = 50;

    public AccordionCreator(List<Sprite> sprites) {
        createAccordion(sprites);
    }

    private void createAccordion(List<Sprite> sprites) {
        HBox hBox = new HBox();
        for(Sprite sprite: sprites){
            ImageView image = new ImageView(new Image(getClass().getClassLoader().getResourceAsStream(sprite.getImagePath())));
            hBox.getChildren().add(image);
            image.setFitHeight(ITEM_HEIGHT);
            image.setFitWidth(ITEM_WIDTH);
        }
        TitledPane titledPane = new TitledPane("Towers & Enemies", hBox);
       // TitledPane enemyPane = new TitledPane("Enemies", hBox);
        //getPanes().add(towersPane);
        getPanes().add(titledPane);
    }

}