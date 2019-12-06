package voogasalad.gameauthoringenvironment.gui.tabconfig;


import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import org.w3c.dom.Document;
import voogasalad.gameauthoringenvironment.bus.Bus;
import voogasalad.gameauthoringenvironment.gui.AddToXML;
import voogasalad.gameauthoringenvironment.gui.levelconfig.LevelConfigPane;
import voogasalad.gameauthoringenvironment.gui.tabconfig.parameterfields.InputFieldCreator;

import javax.xml.parsers.ParserConfigurationException;
import java.io.FileNotFoundException;
import java.util.ResourceBundle;

public class TabPaneCreator {
    private static final String SPRITE_OPTIONS = "resources.gae.SpriteOptions";
    private static final String FIELD_TYPES = "resources.gae.ParamToInputType";
    private static final String TAB_NAMES = "resources.gae.TabNames";
    private static final String ENEMY_ATTRIBUTES = "resources.gae.EnemyAttributes";
    private static final int HEIGHT = 500;

    private ResourceBundle myTabNames;
    private ResourceBundle defaultProperties;
    private ResourceBundle typeToParams;
    private ResourceBundle paramFieldType;
    private TabPane myTabPane;
    private AddToXML sendToXML;
    private Document createdXML;
    private Bus busInstance;
    private BorderPane borderPane;

    public TabPaneCreator(AddToXML sendToXMLParam, Document createdXMLParam, Bus busInstanceParam) {
        sendToXML = sendToXMLParam;
        createdXML = createdXMLParam;
        busInstance = busInstanceParam;
        defaultProperties = ResourceBundle.getBundle(ENEMY_ATTRIBUTES);
        typeToParams = ResourceBundle.getBundle(SPRITE_OPTIONS);
        paramFieldType = ResourceBundle.getBundle(FIELD_TYPES);
        myTabPane = createTabPane();
    }

    /**
     * A getter method to return local variable myTabPane
     * @return a TabPane with tabs already added
     */
    public TabPane getTabPane() {
        return myTabPane;
    }

    // create a TabPane with tab names from a resource file
    private TabPane createTabPane() {

        TabPane tabPane = new TabPane();
        //tabPane.setMaxHeight(height/10);
        myTabNames = ResourceBundle.getBundle(TAB_NAMES);
        createBorderPane(tabPane);

        Tab levelTab = new Tab("Level");
        levelTab.setContent(new LevelConfigPane(sendToXML, createdXML, busInstance));
        tabPane.getTabs().add(levelTab);

        return tabPane;
    }

    // TODO: add exception method
    // a helper method to assemble a BorderPane
    private BorderPane createBorderPane(TabPane tabPane) {

        typeToParams.getKeys().asIterator().forEachRemaining(key -> {
            try {
                Tab tab = new Tab(key, new InputFieldCreator(key, typeToParams.getString(key).split(","), paramFieldType) );
                tabPane.getTabs().add(tab);
            } catch (ParserConfigurationException | FileNotFoundException e) {
                e.printStackTrace();
            }
        });

        return borderPane;
    }

}
