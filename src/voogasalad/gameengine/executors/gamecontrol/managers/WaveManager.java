package voogasalad.gameengine.executors.gamecontrol.managers;

import voogasalad.gameengine.executors.gamecontrol.Wave;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class WaveManager {
    private List<Wave> myWaves;
    private int myIndex;

    public WaveManager() {
        myIndex = 0;
        myWaves = new ArrayList<>();
    }

    public void addWavesCollection(Collection<Wave> waves) {
        myWaves.addAll(waves);
    }

    public Wave getNextWave() {
        return myWaves.get(myIndex++);
    }

    public boolean hasNextWave() {
        return myIndex < myWaves.size();
    }

}
