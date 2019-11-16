package tests.engine.strategies;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import voogasalad.gameengine.engine.exceptions.GameEngineException;
import voogasalad.gameengine.engine.factories.StrategiesFactory;
import voogasalad.gameengine.engine.spritestrategies.movement.MovementStrategy;

import java.awt.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class MovementStrategyTests {
    private StrategiesFactory strategiesFactory = new StrategiesFactory();
    MovementStrategy movement1;

    @BeforeEach
    public void setUp () throws GameEngineException {
        LinkedList<Point> path1 = new LinkedList<>();
        path1.add(new Point(0, 0));
        path1.add(new Point(0, 100));
        //double speed1 = 10;
        Map<String, Object> parameters1 = new HashMap<>() {{
            put("myPath", path1);
            //put("mySpeed", speed1);
        }};
        movement1 = strategiesFactory.makeMovement("PathMovement", parameters1);
    }

    @Test
    public void testCurrentPosition () {
        Point expected = new Point(0, 0);
        movement1.getCurrentPosition();
        assertEquals(expected, movement1.getCurrentPosition());
    }


}