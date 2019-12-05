package voogasalad.gameauthoringenvironment.gui.tabconfig;


import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import org.w3c.dom.Document;
import voogasalad.gameauthoringenvironment.bus.Bus;
import voogasalad.gameauthoringenvironment.gui.AddToXML;
import voogasalad.gameauthoringenvironment.gui.levelconfig.LevelConfigPane;
import voogasalad.gameauthoringenvironment.gui.tabconfig.parameterfields.ParameterCreator;

import javax.xml.parsers.ParserConfigurationException;
import java.util.ResourceBundle;

public class TabPaneCreator {
    private static final String SPRITE_OPTIONS_RESOURCE = "resources.gae.SpriteOptions";
    private static final String PARAM_FIELD_TYPE_RESOURCE = "resources.gae.ParamToInputType";
    private static final String TAB_NAMES = "resources.gae.TabNames";
    private static final String ENEMY_ATTRIBUTES = "resources.gae.EnemyAttributes";

    private ResourceBundle myTabNames;
    private TabPane myTabPane;
    private int height = 500;
    private AddToXML sendToXML;
    private Document createdXML;
    private Bus busInstance;
    private ResourceBundle defaultProperties;
    private ResourceBundle typeToParams;
    private ResourceBundle paramFieldType;
    private BorderPane bp;

    public TabPaneCreator(AddToXML sendToXMLParam, Document createdXMLParam, Bus busInstanceParam) {
        sendToXML = sendToXMLParam;
        createdXML = createdXMLParam;
        busInstance = busInstanceParam;
        defaultProperties = ResourceBundle.getBundle(ENEMY_ATTRIBUTES);
        typeToParams = ResourceBundle.getBundle(SPRITE_OPTIONS_RESOURCE);
        paramFieldType = ResourceBundle.getBundle(PARAM_FIELD_TYPE_RESOURCE);
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

        //refactor to use reflection!!
//        for (String s : myTabNames.keySet()) {
//            String tabName = myTabNames.getString(s);
//            Tab tab = new Tab(s);
//            tabPane.getTabs().add(tab);
//        }


//        Tab towersTab = new TowerConfigTab().getTab();
//        Tab obstaclesTab = new ObstacleConfigTab().getTab();
//        Tab enemiesTab = new Tab("Enemies", new GAE_ObjectConfig("Enemy", defaultProperties));
        createPane(tabPane);
        Tab levelTab = new Tab("Level");
        levelTab.setContent(new LevelConfigPane(sendToXML, createdXML, busInstance));
//
//        tabPane.getTabs().add(towersTab);
//        tabPane.getTabs().add(obstaclesTab);
//        tabPane.getTabs().add(enemiesTab);
        tabPane.getTabs().add(levelTab);


        return tabPane;
    }

    private BorderPane createPane(TabPane tabPane) {
        typeToParams.getKeys().asIterator().forEachRemaining(key -> {
            try {
                Tab objectTab = new Tab(key, new ParameterCreator(key, typeToParams.getString(key).split(","), paramFieldType) );
                tabPane.getTabs().add(objectTab);
            } catch (ParserConfigurationException e) {
                e.printStackTrace();
            }
        });
        return bp;
    }
}
