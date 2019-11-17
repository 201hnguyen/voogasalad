import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class testGAETower extends Application {
    public static final String TITLE = "TestFX Example";
    public static final int SIZE = 300;
    public static final boolean DEBUG = true;

    public void start (Stage primaryStage) {
        GAETowerView newTowerPage = new GAETowerView();
        primaryStage.setTitle(TITLE);
        primaryStage.setScene(newTowerPage.getScene());
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
