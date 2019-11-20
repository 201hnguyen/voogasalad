package voogasalad.gameplayer.GUI;
        import javafx.scene.control.Accordion;
        import javafx.scene.control.TitledPane;
        import javafx.scene.image.Image;
        import javafx.scene.image.ImageView;
        import java.util.Collections;
        import java.util.ResourceBundle;

public class AccordionCreator extends Accordion {
    private static final String RESOURCE_PATH = "resources.player.AccordionResource";
    private ResourceBundle resourceBundle;

    public AccordionCreator() {
        resourceBundle = ResourceBundle.getBundle(RESOURCE_PATH);
        createAccordion();
    }

    private void createAccordion() {
        getChildren().clear();
        for (String key : Collections.list(resourceBundle.getKeys())) {
            ImageView imageView = new ImageView(new Image(this.getClass().getClassLoader().getResourceAsStream(resourceBundle.getString(key))));
            TitledPane pane = new TitledPane(key, imageView);
            getPanes().add(pane);
        }
    }
}