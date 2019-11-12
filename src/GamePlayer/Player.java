package GamePlayer;
import java.awt.*;
import java.io.*;
import java.util.Map;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.text.*;

public class Player {

    public static final String TYPE = "GameConfig";
    public static final int WINDOW_SIZE = 500;
    private String myXMLPath;
    private XMLParser myXMLParser;
    private Stage myStage;
    private Group mapRoot;
    private Map<ImageView, Point[]> components;

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
        String win = myXMLParser.getTextValue("WindowSize");
        String[] windowSize = win.split(" ");
        Scene scene = new Scene(mapRoot, Double.parseDouble(windowSize[0]), Double.parseDouble(windowSize[1]));
        myStage.setScene(scene);
        String dim = myXMLParser.getTextValue("Dimensions");
        String[] dimensions = dim.split(" ");

        Double cell_size_x = Double.parseDouble(windowSize[0])/Double.parseDouble(dimensions[0]);
        Double cell_size_y = Double.parseDouble(windowSize[1])/Double.parseDouble(dimensions[1]);

        myStage.show();


    }



    public void loadXML(String xmlPath){
        myXMLPath = xmlPath;
        myXMLParser = new XMLParser(TYPE, new File(xmlPath));
    }

    public String getXML(){
        return myXMLPath;
    }
}
