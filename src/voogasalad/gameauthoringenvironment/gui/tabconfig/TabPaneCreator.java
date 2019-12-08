package voogasalad.gameauthoringenvironment.gui.tabconfig;


import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import org.w3c.dom.Document;
import voogasalad.gameauthoringenvironment.bus.Bus;
import voogasalad.gameauthoringenvironment.gui.AddToXML;
import voogasalad.gameauthoringenvironment.gui.levelconfig.LevelConfigPane;
import voogasalad.gameauthoringenvironment.gui.tabconfig.parameterfields.ObjectPreviewAndActive;
import voogasalad.gameauthoringenvironment.gui.tabconfig.parameterfields.ParameterCreator;
import voogasalad.gameengine.executors.control.levelcontrol.Level;

import javax.xml.parsers.ParserConfigurationException;
import java.util.*;

public class TabPaneCreator {
    private static final String SPRITE_OPTIONS_RESOURCE = "resources.gae.tabcreation.SpriteOptions";
    private static final String PARAM_FIELD_TYPE_RESOURCE = "resources.gae.tabcreation.ParamToInputType";

    private TabPane myTabPane;
    private AddToXML sendToXML;
    private Document createdXML;
    private Bus busInstance;
    private ResourceBundle typeToParams;
    private ResourceBundle paramFieldType;
    private BorderPane bp;
    private LevelConfigPane levelConfigPane;
    //private Map<String, Map<String, Map<String, String>>> allActiveObjects;
    private Map<String, Map<String, String>> allActiveObjects;
    private List<ObjectPreviewAndActive> allActiveObjectObjects;

    public TabPaneCreator(AddToXML sendToXMLParam, Document createdXMLParam, Bus busInstanceParam) {
        allActiveObjectObjects = new ArrayList<>();
        allActiveObjects = new HashMap<>();
        sendToXML = sendToXMLParam;
        createdXML = createdXMLParam;
        busInstance = busInstanceParam;
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

    private TabPane createTabPane() {
        levelConfigPane = new LevelConfigPane(sendToXML, createdXML, busInstance, allActiveObjects, allActiveObjectObjects);
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
                Tab objectTab = new Tab(key, new ParameterCreator(key, typeToParams.getString(key).split(","), paramFieldType, levelConfigPane, allActiveObjects, allActiveObjectObjects));
                tabPane.getTabs().add(objectTab);
            } catch (ParserConfigurationException e) {
                e.printStackTrace();
            }
        });
        return bp;
    }
}
