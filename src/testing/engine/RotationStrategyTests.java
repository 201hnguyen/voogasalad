package testing.engine;

import javafx.util.Pair;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import voogasalad.gameengine.executors.exceptions.GameEngineException;
import voogasalad.gameengine.executors.sprites.strategies.rotation.RotationStrategy;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class RotationStrategyTests {

    RotationStrategy FullRotation;
    RotationStrategy LimitedRotation;
    RotationStrategy NoRotation;

    @Test
    public void testGetCurrentAngleFull(){
        Double expected = 0.0;
        assertEquals(expected, 0);
    }
}
