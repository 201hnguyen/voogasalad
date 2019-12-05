package voogasalad.gameplayer.GUI;
import javafx.scene.control.Accordion;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import voogasalad.gameengine.executors.sprites.Sprite;

        import java.util.List;

public class AccordionCreator extends Accordion {
    public static final int ITEM_HEIGHT = 50;
    public static final int ITEM_WIDTH = 50;
    public static final String TOWER = "Towers";
    private static final String ENEMY = "Enemies";
    private HBox hBoxTowers;
    private HBox hBoxEnemies;


    public AccordionCreator() {
        createAccordion();
    }

    private void createAccordion() {
        hBoxTowers = new HBox();
        hBoxEnemies = new HBox();
        TitledPane towerPane = new TitledPane(TOWER, hBoxTowers);
        TitledPane enemyPane = new TitledPane(ENEMY, hBoxEnemies);
        getPanes().add(towerPane);
        getPanes().add(enemyPane);
    }

    public void updateAvailableTowersAndEnemies(List<Sprite> towers, List<Sprite> enemies){
        hBoxTowers.getChildren().clear();
        hBoxEnemies.getChildren().clear();
        for(Sprite tower: towers){
            ImageView image = new ImageView(new Image(getClass().getClassLoader().getResourceAsStream(tower.getImagePath())));
            hBoxTowers.getChildren().add(image);
            image.setFitHeight(ITEM_HEIGHT);
            image.setFitWidth(ITEM_WIDTH);
        }
        for(Sprite enemy: enemies){
            ImageView image = new ImageView(new Image(getClass().getClassLoader().getResourceAsStream(enemy.getImagePath())));
            hBoxEnemies.getChildren().add(image);
            image.setFitHeight(ITEM_HEIGHT);
            image.setFitWidth(ITEM_WIDTH);
        }
    }

}