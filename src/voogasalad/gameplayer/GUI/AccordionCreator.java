package voogasalad.gameplayer.GUI;
        import javafx.scene.control.Accordion;
        import javafx.scene.control.TitledPane;
        import javafx.scene.image.Image;
        import javafx.scene.image.ImageView;
        import javafx.scene.layout.HBox;
        import voogasalad.gameengine.engine.sprites.JavaFXSprite;

        import java.util.List;

public class AccordionCreator extends Accordion {
//    private static final String RESOURCE_PATH = "resources.player.AccordionResource";
//    private ResourceBundle resourceBundle;

    private JavaFXSprite javaFXSprite;

    final String[] imageNames = new String[]{"bird", "pandaslogo"};
    final Image[] images = new Image[imageNames.length];
    final ImageView[] pics = new ImageView[imageNames.length];
    private List<JavaFXSprite> sprites;

    public AccordionCreator(List<JavaFXSprite> sprites) {
        this.sprites = sprites;
        createAccordion();
    }

    private void createAccordion() {
        HBox hBox = new HBox();
        for(JavaFXSprite sprite: sprites){
            hBox.getChildren().add(sprite.getImageView());
        }
        TitledPane titledPane = new TitledPane("trial", hBox);
        getPanes().add(titledPane);
    }

   /* public AccordionCreator() {
        resourceBundle = ResourceBundle.getBundle(RESOURCE_PATH);
        createAccordion();
    }

    private void createAccordion() {
        HBox hBox = new HBox();
        for (String key : Collections.list(resourceBundle.getKeys())) {
            ImageView imageView = new ImageView(new Image(this.getClass().getClassLoader().getResourceAsStream(resourceBundle.getString(key))));
            hBox.getChildren().add(imageView);
            TitledPane pane = new TitledPane(key, hBox);
            getPanes().add(pane);
        }
    }*/
}