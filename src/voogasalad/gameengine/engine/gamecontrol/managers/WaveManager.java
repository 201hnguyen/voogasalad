package voogasalad.gameengine.engine.gamecontrol.managers;

import voogasalad.gameengine.engine.gamecontrol.Wave;

import java.util.List;

public class WaveManager {
    private List<Wave> myWaves;
    private int myIndex;

    public WaveManager(List<Wave> waves) {
        myIndex = 0;
        myWaves = waves;
    }

    public Wave getNextWave() {
        return myWaves.get(myIndex++);
    }

    public boolean hasNextWave() {
        return myIndex < myWaves.size();
    }

}
