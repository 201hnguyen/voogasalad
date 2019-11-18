package voogasalad.gameplayer;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Player {

    public static final String TYPE = "GameConfig";
    public static final int WINDOW_SIZE = 500;
    private String myXMLPath;
    private XMLParser myXMLParser;
    private Stage myStage;
    private Group mapRoot;

    //Player expects a javaFX Stage upon instantiation
    public Player(Stage primaryStage, String xmlPath){
        myStage = primaryStage;
        mapRoot = new Group();
        loadXML(xmlPath);
    }

    public String startGame(){
        displayMapFromXML();
        return "";
    }

    private void displayMapFromXML(){
        Scene scene = new Scene(mapRoot, 1000, 800);
        myStage.setScene(scene);
        myStage.show();
        String[] componentTypes = {"Tower", "Enemy"};
        for(String component: componentTypes) {
            ArrayList<Map<String, String>> componentAttributeMap = myXMLParser.getAttributesByTagName(component);
            for(Map<String, String> comp: componentAttributeMap){
                for(String s: comp.keySet()){
                    instantiateComponentAndAddToList(s);
                }
            }
        }
    }

    private void instantiateComponentAndAddToList(String component) {

    }


    public void loadXML(String xmlPath){
        myXMLPath = xmlPath;
        myXMLParser = new XMLParser(TYPE, new File(xmlPath));
    }

    public String getXML(){
        return myXMLPath;
    }
}
