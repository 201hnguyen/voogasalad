package voogasalad.everything_gae.gae_gui.TabConfig;

import javafx.application.Application;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import org.w3c.dom.Document;
import voogasalad.everything_gae.bus.Bus;
import voogasalad.everything_gae.gae_gui.AddToXML;
import voogasalad.everything_gae.gae_gui.level_map_config.level_config.LevelConfigPane;

import java.util.ResourceBundle;

public class TabPaneCreator {
//public class TabCreator<label> {
    private static final String TAB_NAMES = "voogasalad/everything_gae/resources/TabNames";
    private ResourceBundle myTabNames;
    private TabPane myTabPane;
    private int height = 500;
    private AddToXML sendToXML;
    private Document createdXML;
    private Bus busInstance;

    public TabPaneCreator(AddToXML sendToXMLParam, Document createdXMLParam, Bus busInstanceParam) {
        sendToXML = sendToXMLParam;
        createdXML = createdXMLParam;
        busInstance = busInstanceParam;
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


        Tab towersTab = new TowerConfigTab().getTab();
        Tab obstaclesTab = new ObstacleConfigTab().getTab();
        Tab enemiesTab = new EnemyConfigTab().getTab();
        Tab levelTab = new Tab("Level");
        levelTab.setContent(new LevelConfigPane(sendToXML, createdXML, busInstance));

        tabPane.getTabs().add(towersTab);
        tabPane.getTabs().add(obstaclesTab);
        tabPane.getTabs().add(enemiesTab);
        tabPane.getTabs().add(levelTab);


        return tabPane;
    }
}
