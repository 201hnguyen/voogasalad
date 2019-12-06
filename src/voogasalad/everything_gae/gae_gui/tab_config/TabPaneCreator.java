package voogasalad.everything_gae.gae_gui.tab_config;


import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import org.w3c.dom.Document;
import voogasalad.everything_gae.bus.Bus;
import voogasalad.everything_gae.gae_gui.AddToXML;
import voogasalad.everything_gae.gae_gui.level_map_config.level_config.LevelConfigPane;
import voogasalad.everything_gae.gae_gui.tab_config.object_param_creation.CreateObjectParams;

import javax.xml.parsers.ParserConfigurationException;
import java.util.ResourceBundle;

public class TabPaneCreator {
//public class TabCreator<label> {
    public static final String SPRITE_OPTIONS_RESOURCE = "voogasalad/everything_gae/resources/SpriteOptions";
    public static final String PARAM_FIELD_TYPE_RESOURCE = "voogasalad/everything_gae/resources/ParamToInputType";
    private static final String TAB_NAMES = "voogasalad/everything_gae/resources/TabNames";
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
        defaultProperties = ResourceBundle.getBundle("voogasalad/everything_gae/resources/EnemyAttributes");
        typeToParams = ResourceBundle.getBundle(SPRITE_OPTIONS_RESOURCE);
        paramFieldType = ResourceBundle.getBundle(PARAM_FIELD_TYPE_RESOURCE);
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
                Tab objectTab = new Tab(key, new CreateObjectParams(key, typeToParams.getString(key).split(","), paramFieldType) );
                tabPane.getTabs().add(objectTab);
            } catch (ParserConfigurationException e) {
                e.printStackTrace();
            }
        });
        return bp;
    }
}
