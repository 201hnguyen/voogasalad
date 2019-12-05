package voogasalad.everything_gae.gae_gui.TabConfig;

import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.VBox;

public class EnemyConfigTab extends ConfigTabTemplate {


    private Tab myEnemyConfigTab;
    private TabPane myTabPane;


    public EnemyConfigTab() {

        myEnemyConfigTab = addToTab();
    }

    /**
     * A getter method to retrieve the local variable myTowerConfigTab
     * @return
     */
    public Tab getTab() {
        return myEnemyConfigTab;
    }

    // Creates a tab for the TowerConfig
    public Tab addToTab() {

        myEnemyConfigTab = new Tab("Enemies");

        //create node content for tab
        VBox configVBox = new VBox();
        Label configLabel = new Label("Configuration");


        //add node content to VBox
        configVBox.getChildren().addAll(
                configLabel);

        //add VBox to tab
        myEnemyConfigTab.setContent(configVBox);

        return myEnemyConfigTab;
    }

}
