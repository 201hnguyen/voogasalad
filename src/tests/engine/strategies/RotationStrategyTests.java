package tests.engine.strategies;

import javafx.util.Pair;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import voogasalad.gameengine.executors.exceptions.GameEngineException;
import voogasalad.gameengine.executors.objectcreators.StrategiesFactory;
import voogasalad.gameengine.executors.sprites.strategies.rotation.RotationStrategy;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

//TODO: REWRITE TESTS FOR CONTINUOUS ROTATION

public class RotationStrategyTests {
    private StrategiesFactory factory = new StrategiesFactory();
    RotationStrategy FullRotation;
    RotationStrategy LimitedRotation;
    RotationStrategy NoRotation;

    @BeforeEach
    public void setUp() throws GameEngineException {
        //add test info to map
        Map<String, Object> fullRotationParameters = new HashMap<>();
        fullRotationParameters.put("angle", 0.0);
        fullRotationParameters.put("rotation_speed", 40.0);
        fullRotationParameters.put("rotation_range", new Pair(0, 0));

        Map<String, Object> noRotationParameters = new HashMap<>();
        noRotationParameters.put("angle", 70.0);
        noRotationParameters.put("rotation_speed", 0.0);
        noRotationParameters.put("rotation_range", new Pair(0, 0));

        Map<String, Object> limitedRotationParameters = new HashMap<>();
        limitedRotationParameters.put("angle", 55.5);
        limitedRotationParameters.put("rotation_speed", 40.0);
        limitedRotationParameters.put("rotation_range", new Pair(40.0, 80.0));

        //create strat from factory
        FullRotation = factory.makeRotation("FullRotationStrategy", fullRotationParameters);
        NoRotation = factory.makeRotation("NoRotationStrategy", noRotationParameters);
        LimitedRotation = factory.makeRotation("LimitedRotationStrategy", limitedRotationParameters);
    }

    @Test
    public void testGetCurrentAngleFull(){
        Double expected = 0.0;
        assertEquals(expected, FullRotation.getCurrentAngle());
    }

    @Test
    void testGetCurrentAngleNo(){
        Double expected = 70.0;
        assertEquals(expected, NoRotation.getCurrentAngle());
    }

    @Test
    void testGetCurrentAngleLimited(){
        Double expected = 55.5;
        assertEquals(expected, LimitedRotation.getCurrentAngle());
    }

    @Test
    public void testDetermineRotationDirectionFull(){
        int expected = 1;
        FullRotation.determineRotationDirection();
        assertEquals(expected, FullRotation.getRotationDirection());
    }

    @Test
    //TODO: this actually works as expected, but idk how to make a test to verify that
    public void testUpdateAngleFull(){
        Double expected = 10.0;
        Double mockTime = 0.0;
        while(mockTime < 100){
            FullRotation.updateAngle(.01666);
            //System.out.println("current angle: " + (double) FullRotation.getCurrentAngle());
            //System.out.println("current time: " + mockTime);
            mockTime+= .1;
        }
        assertEquals(expected, FullRotation.getCurrentAngle(), .1);
    }

    @Test
    public void testUpdateAngleNo(){
        Double expected = 70.0;
        Double mockTime = 0.0;
        while (mockTime < 100){
            NoRotation.updateAngle(.01666);
            mockTime+= .1;
        }
        assertEquals(expected, NoRotation.getCurrentAngle(), .1);
    }

    @Test
    //TODO: this actually works as expected, but idk how to make a test to verify that
    public void testUpdateAngleLimited(){
        Double expected = 80.0;
        Double mockTime = 0.0;
        while (mockTime < 100){
            LimitedRotation.updateAngle(.01666);
            System.out.println("current angle: " + (double) LimitedRotation.getCurrentAngle());
            System.out.println("current time: " + mockTime);
            mockTime+= .1;
        }
        assertEquals(expected, LimitedRotation.getCurrentAngle(), 0.1);
    }
}
