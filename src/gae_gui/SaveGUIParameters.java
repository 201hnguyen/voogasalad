package gae_gui;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class SaveGUIParameters {

    private Map<String, String> vBoxMap;

    public SaveGUIParameters(List labels, List values) {
        vBoxMap = new HashMap<>();
        this.vBoxMap = makeMap(labels, values);
    }

    private Map makeMap(List labels, List values) {

        Iterator iterLabels = labels.iterator();
        Iterator iterValues = values.iterator();
        while(iterLabels.hasNext() && iterValues.hasNext()) {
            vBoxMap.put((String)iterLabels.next(), (String)iterValues.next());
        }

        for (Map.Entry s : vBoxMap.entrySet()) {
            System.out.println(s);
        }

        return vBoxMap;
    }

    public Map getMap(){
        return vBoxMap;
    }



}
