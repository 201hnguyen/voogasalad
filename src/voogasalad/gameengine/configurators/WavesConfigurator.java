package voogasalad.gameengine.configurators;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import voogasalad.gameengine.executors.exceptions.GameEngineException;
import voogasalad.gameengine.executors.control.levelcontrol.Wave;
import voogasalad.gameengine.executors.sprites.Sprite;
import voogasalad.gameengine.executors.utils.ConfigurationTool;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.*;
import java.util.List;

public class WavesConfigurator {

    public static final String WAVE_QUEUE_KEY = "WaveQueueNodeTag";
    public static final String WAVE_INTERVAL_KEY = "WaveIntervalNodeTag";
    public static final String WAVE_SPAWN_POINT_X_KEY = "SpawnPointXNodeTag";
    public static final String WAVE_SPAWN_POINT_Y_KEY = "SpawnPointYNodeTag";
    public static final String PATH_KEY = "PathNodeTag";

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
        String[] spriteQueueAsStrings = myDefinedWave.getElementsByTagName(GameConfigurator.GAME_CONFIGURATION_RESOURCE_BUNDLE.getString(WAVE_QUEUE_KEY)).item(0).getTextContent().split(" ");
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
        return Double.parseDouble(myDefinedWave.getElementsByTagName(GameConfigurator.GAME_CONFIGURATION_RESOURCE_BUNDLE.getString(WAVE_INTERVAL_KEY)).item(0).getTextContent());
    }

    private Point parseSpawnPoint() {
        Integer x = Integer.parseInt(myDefinedWave.getElementsByTagName(GameConfigurator.GAME_CONFIGURATION_RESOURCE_BUNDLE.getString(WAVE_SPAWN_POINT_X_KEY)).item(0).getTextContent());
        Integer y = Integer.parseInt(myDefinedWave.getElementsByTagName(GameConfigurator.GAME_CONFIGURATION_RESOURCE_BUNDLE.getString(WAVE_SPAWN_POINT_Y_KEY)).item(0).getTextContent());
        return new Point(x, y);
    }

    private List<Point2D.Double> parsePath() {
        String pathString = myDefinedWave.getElementsByTagName(GameConfigurator.GAME_CONFIGURATION_RESOURCE_BUNDLE.getString(PATH_KEY)).item(0).getTextContent();
        return ConfigurationTool.parsePath(pathString);
    }
}
