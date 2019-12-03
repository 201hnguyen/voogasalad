package voogasalad.gameengine.configurators.test;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import voogasalad.gameengine.executors.gamecontrol.Wave;

import java.awt.*;
import java.util.*;
import java.util.List;

public class WavesConfigurator {

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
        String[] spriteQueueAsStrings = definedWave.getElementsByTagName("Queue").item(0).getTextContent().split(" ");
        Queue<Integer> spriteQueue = new LinkedList<>();
        for (String s : spriteQueueAsStrings) {
            spriteQueue.add(Integer.parseInt(s));
        }
        return spriteQueue;
    }

    private Double parseInterval(Element definedWave) {
        return Double.parseDouble(definedWave.getElementsByTagName("Interval").item(0).getTextContent());
    }

    private Point parseSpawnPoint(Element definedWave) {
        Integer x = Integer.parseInt(definedWave.getElementsByTagName("SpawnPointX").item(0).getTextContent());
        Integer y = Integer.parseInt(definedWave.getElementsByTagName("SpawnPointY").item(0).getTextContent());
        return new Point(x, y);
    }
}
