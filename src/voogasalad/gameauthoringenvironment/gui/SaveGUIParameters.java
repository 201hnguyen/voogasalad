package voogasalad.gameauthoringenvironment.gui;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class SaveGUIParameters {

    private Map<String, String> inputMap;

    public SaveGUIParameters(List labels, List values) {
        inputMap = new HashMap<>();
        this.inputMap = makeMap(labels, values);
    }

    private Map makeMap(List labels, List values) {

        Iterator iterLabels = labels.iterator();
        Iterator iterValues = values.iterator();
        while(iterLabels.hasNext() && iterValues.hasNext()) {
            inputMap.put((String)iterLabels.next(), (String)iterValues.next());
        }

        for (Map.Entry s : inputMap.entrySet()) {
            System.out.println(s);
        }

        return inputMap;
    }

    public Map<String, String> getMap(){
        return inputMap;
    }



}
