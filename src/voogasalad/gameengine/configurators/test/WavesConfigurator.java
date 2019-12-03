package voogasalad.gameengine.configurators.test;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import voogasalad.gameengine.executors.gamecontrol.Wave;

import java.awt.*;
import java.util.*;
import java.util.List;

public class WavesConfigurator {

    public static final String WAVE_QUEUE_TAG = "Queue";
    public static final String WAVE_INTERVAL_TAG = "Interval";
    public static final String WAVE_SPAWN_POINT_X_TAG = "SpawnPointX";
    public static final String WAVE_SPAWN_POINT_Y_TAG = "SpawnPointY";

    public Collection<Wave> buildWavesCollection(NodeList waveNodesList) {
        List<Wave> wavesCollection = new ArrayList<>();
        for (int i=0; i<waveNodesList.getLength(); i++) {
            Element definedWave = (Element) waveNodesList.item(i);
            Queue<Integer> waveQueue = parseQueue(definedWave);
            Double interval = parseInterval(definedWave);
            Point spawnPoint = parseSpawnPoint(definedWave);
            Wave wave = new Wave(waveQueue, interval, spawnPoint);
            wavesCollection.add(wave);
        }
        return wavesCollection;
    }

    private Queue<Integer> parseQueue(Element definedWave) {
        String[] spriteQueueAsStrings = definedWave.getElementsByTagName(WAVE_QUEUE_TAG).item(0).getTextContent().split(" ");
        Queue<Integer> spriteQueue = new LinkedList<>();
        for (String s : spriteQueueAsStrings) {
            spriteQueue.add(Integer.parseInt(s));
        }
        return spriteQueue;
    }

    private Double parseInterval(Element definedWave) {
        return Double.parseDouble(definedWave.getElementsByTagName(WAVE_INTERVAL_TAG).item(0).getTextContent());
    }

    private Point parseSpawnPoint(Element definedWave) {
        Integer x = Integer.parseInt(definedWave.getElementsByTagName(WAVE_SPAWN_POINT_X_TAG).item(0).getTextContent());
        Integer y = Integer.parseInt(definedWave.getElementsByTagName(WAVE_SPAWN_POINT_Y_TAG).item(0).getTextContent());
        return new Point(x, y);
    }
}
