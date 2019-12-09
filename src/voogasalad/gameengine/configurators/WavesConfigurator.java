package voogasalad.gameengine.configurators;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import voogasalad.gameengine.executors.exceptions.GameEngineException;
import voogasalad.gameengine.executors.control.levelcontrol.Wave;
import voogasalad.gameengine.executors.sprites.Sprite;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.*;
import java.util.List;

public class WavesConfigurator {

    public static final String WAVE_QUEUE_TAG = "Queue";
    public static final String WAVE_INTERVAL_TAG = "Interval";
    public static final String WAVE_SPAWN_POINT_X_TAG = "SpawnPointX";
    public static final String WAVE_SPAWN_POINT_Y_TAG = "SpawnPointY";
    public static final String PATH_TAG = "Path";

    private List<Integer> myAvailablePrototypeIds;
    private Element myDefinedWave;

    public Collection<Wave> buildWavesCollection(NodeList waveNodesList, List<Sprite> availablePrototypesList) throws GameEngineException {
        List<Wave> wavesCollection = new ArrayList<>();
        myAvailablePrototypeIds = calculateAvailablePrototypeIds(availablePrototypesList);
        for (int i=0; i<waveNodesList.getLength(); i++) {
            myDefinedWave = (Element) waveNodesList.item(i);
            Queue<Integer> waveQueue = parseQueue();
            Double interval = parseInterval();
            Point spawnPoint = parseSpawnPoint();
            List<Point2D.Double> path = parsePath();
            Wave wave = new Wave(waveQueue, interval, spawnPoint, path);
            wavesCollection.add(wave);
        }
        return wavesCollection;
    }

    private List<Integer> calculateAvailablePrototypeIds(List<Sprite> availableSpritePrototypes) {
        List<Integer> spriteIds = new ArrayList<>();
        for (Sprite prototype : availableSpritePrototypes) {
            spriteIds.add(prototype.getPrototypeId());
        }
        return spriteIds;
    }

    private Queue<Integer> parseQueue() throws GameEngineException {
        String[] spriteQueueAsStrings = myDefinedWave.getElementsByTagName(WAVE_QUEUE_TAG).item(0).getTextContent().split(" ");
        Queue<Integer> spriteQueue = new LinkedList<>();
        for (String s : spriteQueueAsStrings) {
            if (myAvailablePrototypeIds.contains(Integer.parseInt(s))) {
                spriteQueue.add(Integer.parseInt(s));
            } else {
                throw new GameEngineException("InvalidPrototypeInWave");
            }
        }
        return spriteQueue;
    }

    private Double parseInterval() {
        return Double.parseDouble(myDefinedWave.getElementsByTagName(WAVE_INTERVAL_TAG).item(0).getTextContent());
    }

    private Point parseSpawnPoint() {
        Integer x = Integer.parseInt(myDefinedWave.getElementsByTagName(WAVE_SPAWN_POINT_X_TAG).item(0).getTextContent());
        Integer y = Integer.parseInt(myDefinedWave.getElementsByTagName(WAVE_SPAWN_POINT_Y_TAG).item(0).getTextContent());
        return new Point(x, y);
    }

    private List<Point2D.Double> parsePath() {
        String pathString = myDefinedWave.getElementsByTagName(PATH_TAG).item(0).getTextContent();
        List<Point2D.Double> parsedPath = new ArrayList<>();
        String[] pointStrings = pathString.strip().split(";");
        for(String pointString : pointStrings) {
            Point2D.Double toAdd = new Point2D.Double();
            String[] coordinateStrings = pointString.split(",");
            toAdd.setLocation(Double.parseDouble(coordinateStrings[0]), Double.parseDouble(coordinateStrings[1]));
            parsedPath.add(toAdd);
        }
        return parsedPath;
    }
}
