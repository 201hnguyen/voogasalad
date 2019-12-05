package voogasalad.everything_gae.gae_gui.tab_config;

import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.VBox;

public class ObstacleConfigTab extends ConfigTabTemplate {


    private Tab myObstacleConfigTab;
    private TabPane myTabPane;


    public ObstacleConfigTab() {

        myObstacleConfigTab = addToTab();
    }

    /**
     * A getter method to retrieve the local variable myObstacleConfigTab
     * @return
     */
    public Tab getTab() {
        return myObstacleConfigTab;
    }

    // Creates a tab for the TowerConfig
    public Tab addToTab() {

        myObstacleConfigTab = new Tab("Obstacles");
        VBox configVBox = new VBox();

        //create node content for tab
        Label configLabel = new Label("Configuration");


        //add node content to VBox
        configVBox.getChildren().addAll(
                configLabel);


        //add VBox to tab
        myObstacleConfigTab.setContent(configVBox);


        return myObstacleConfigTab;
    }

}
