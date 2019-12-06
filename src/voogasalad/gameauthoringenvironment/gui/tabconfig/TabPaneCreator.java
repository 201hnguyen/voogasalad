package voogasalad.gameauthoringenvironment.gui.tabconfig;


import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import org.w3c.dom.Document;
import voogasalad.gameauthoringenvironment.bus.Bus;
import voogasalad.gameauthoringenvironment.gui.AddToXML;
import voogasalad.gameauthoringenvironment.gui.levelconfig.LevelConfigPane;
import voogasalad.gameauthoringenvironment.gui.tabconfig.parameterfields.ParameterCreator;
import voogasalad.gameengine.executors.control.levelcontrol.Level;

import javax.xml.parsers.ParserConfigurationException;
import java.io.FileNotFoundException;
import java.util.ResourceBundle;

public class TabPaneCreator {
    private static final String SPRITE_OPTIONS_RESOURCE = "resources.gae.SpriteOptions";
    private static final String PARAM_FIELD_TYPE_RESOURCE = "resources.gae.ParamToInputType";
    private static final String ENEMY_ATTRIBUTES = "resources.gae.EnemyAttributes";
    private static final int HEIGHT = 500;

    private TabPane myTabPane;
    private AddToXML sendToXML;
    private Document createdXML;
    private Bus busInstance;
    private ResourceBundle defaultProperties;
    private ResourceBundle typeToParams;
    private ResourceBundle paramFieldType;
    private BorderPane bp;
    private LevelConfigPane levelConfigPane;

    public TabPaneCreator(AddToXML sendToXMLParam, Document createdXMLParam, Bus busInstanceParam) {
        sendToXML = sendToXMLParam;
        createdXML = createdXMLParam;
        busInstance = busInstanceParam;
        defaultProperties = ResourceBundle.getBundle(ENEMY_ATTRIBUTES);
        typeToParams = ResourceBundle.getBundle(SPRITE_OPTIONS);
        paramFieldType = ResourceBundle.getBundle(FIELD_TYPES);
        myTabPane = createTabPane();
        myTabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
    }

    /**
     * A getter method to return local variable myTabPane
     * @return a TabPane with tabs already added
     */
    public TabPane getTabPane() {
        return myTabPane;
    }

    private TabPane createTabPane() {
        levelConfigPane = new LevelConfigPane(sendToXML, createdXML, busInstance);
        TabPane tabPane = new TabPane();
        createPane(tabPane, levelConfigPane);
        Tab levelTab = new Tab("Level");
        levelTab.setContent(levelConfigPane);
        tabPane.getTabs().add(levelTab);

        return tabPane;
    }

    private BorderPane createPane(TabPane tabPane, LevelConfigPane levelConfigPane) {
        typeToParams.getKeys().asIterator().forEachRemaining(key -> {
            try {
                Tab objectTab = new Tab(key, new ParameterCreator(key, typeToParams.getString(key).split(","), paramFieldType, levelConfigPane) );
                tabPane.getTabs().add(objectTab);
            } catch (ParserConfigurationException e) {
                e.printStackTrace();
            }
        });

        return borderPane;
    }

}
