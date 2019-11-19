package gae_gui;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class AddToXML {

    private DocumentBuilder builder;
    private String gameObjectName;
    private String[] properties;
    private String GAME_CONFIG = "GameConfig";
    private String SCREEN_WIDTH = "ScreenWidth";
    private String ROWS = "Rows";

    public AddToXML(String gameObjectNameParam, String[] propertiesParam) throws ParserConfigurationException {
        gameObjectName = gameObjectNameParam;
        properties = propertiesParam;
        builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        addToXml();
    }


    private Document addToXml(){
        Document doc = builder.newDocument();
        Element gameConfig = doc.createElement(GAME_CONFIG);
        doc.appendChild(gameConfig);
        Element screenWidth = doc.createElement(SCREEN_WIDTH);
        screenWidth.setNodeValue("400");
        Element rows = doc.createElement(ROWS);
        rows.setNodeValue("4");
        gameConfig.appendChild(screenWidth);
        gameConfig.appendChild(rows);


        //Element screenWidth = doc.createElement("400");
        //Element row = doc.createElement("4");
//        doc.appendChild(gameConfig);
//        doc.appendChild(windowSize);
//        doc.appendChild(dimensions);
       // doc.appendChild(screenWidth);
        //doc.appendChild(row);
        //windowSize.appendChild(screenWidth);
        //dimensions.appendChild(row);

//        Element testsuites = doc.createElement(gameObjectName);
//        Element testsuite = doc.createElement("testsuite");
//        testsuite.setAttribute("name", suiteName);
//        testsuite.setAttribute("tests", "1");
//        testsuite.setAttribute("failures", testResult.equals("FAIL") ? "1": "0");
//        testsuite.setAttribute("pass", testResult.equals("PASS") ? "1": "0");
//
//        Element testcase = doc.createElement("testcase");
//        testcase.setAttribute("name", testName);
//        testcase.setAttribute("status", testResult);
//
//        if(testResult.equals("FAIL")){
//            Element failure = doc.createElement("failure");
//            testcase.appendChild(failure);
//        }
//
//        doc.appendChild(testsuites);
//        testsuites.appendChild(testsuite);
//        testsuite.appendChild(testcase);
//
//        return doc;


        return doc;
    }


}
