package voogasalad.everything_gae.gae_gui.TabConfig;

import javafx.application.Application;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

import java.util.ResourceBundle;

public class TabPaneCreator {
//public class TabCreator<label> {
    private static final String TAB_NAMES = "voogasalad/everything_gae/resources/TabNames";
    private ResourceBundle myTabNames;
    private TabPane myTabPane;

    public TabPaneCreator() {
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
        myTabNames = ResourceBundle.getBundle(TAB_NAMES);

        for (String s : myTabNames.keySet()) {
            String sceneName = myTabNames.getString(s);
            Tab tab = new Tab(s);
            //tab.setContent(sceneName); // refactor to use reflection
            tabPane.getTabs().add(tab);
        }

        return tabPane;
    }
}
