package voogasalad;

import voogasalad.gameengine.engine.exceptions.GameEngineException;

/**
 * Feel free to completely change this code or delete it entirely. 
 */
public class Main {
    /**
     * Start of the program.
     */
    public static void main (String[] args) {
        try {
            MockPlayer player = new MockPlayer();
        } catch (GameEngineException e) {
            e.printStackTrace();
        }
    }
}
