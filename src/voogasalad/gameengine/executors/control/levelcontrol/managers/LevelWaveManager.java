package voogasalad.gameengine.executors.control.levelcontrol.managers;

import voogasalad.gameengine.executors.control.levelcontrol.Wave;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class LevelWaveManager {
    private List<Wave> myWaves;
    private int myIndex;

    public LevelWaveManager() {
        myIndex = 0;
        myWaves = new ArrayList<>();
    }

    public void addWavesCollection(Collection<Wave> waves) {
        myWaves.addAll(waves);
    }

    public Wave getNextWave() {
        int index = myIndex;
        myIndex++;
        return myWaves.get(index);
    }

    public boolean hasNextWave() {
//        System.out.println("Index:" + myIndex + " " + myWaves);
        return myIndex < myWaves.size();
    }

}
