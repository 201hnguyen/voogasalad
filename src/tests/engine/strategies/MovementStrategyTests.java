package tests.engine.strategies;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import voogasalad.gameengine.engine.exceptions.GameEngineException;
import voogasalad.gameengine.engine.factories.StrategiesFactory;
import voogasalad.gameengine.engine.sprites.strategies.movement.MovementStrategy;

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

    }

    @Test
    public void testCurrentPosition () {
        Point expected = new Point(0, 0);
        LinkedList<Point> points = new LinkedList<>();
        points.add(new Point(0, 0));
        points.add(new Point(0, 100));
        MovementStrategy toTest = makeMovementStrategy(points, 10);
        assertEquals(expected, toTest.getCurrentPosition());
    }

    @Test
    public void testUpdatePosition () {
        Point expected = new Point(0, 10);
        LinkedList<Point> points = new LinkedList<>();
        points.add(new Point(0, 0));
        points.add(new Point(0, 100));
        MovementStrategy toTest = makeMovementStrategy(points, 10);
        toTest.updatePosition(1.00);
        assertEquals(expected, toTest.getCurrentPosition());
    }

    @Test
    public void testUpdatePositionComplex () {
        Point expected = new Point(94, 92);
        LinkedList<Point> points = new LinkedList<>();
        points.add(new Point(100, 100));
        points.add(new Point(70, 60));
        MovementStrategy toTest = makeMovementStrategy(points, 10);
        toTest.updatePosition(1.00);
        assertEquals(expected, toTest.getCurrentPosition());
    }

    @Test
    public void testNoNextPositionUpdatePosition () {
        LinkedList<Point> points = new LinkedList<>();
        points.add(new Point(0, 0));
        MovementStrategy toTest = makeMovementStrategy(points, 10);
        toTest.updatePosition(1.00);
    }

    private MovementStrategy makeMovementStrategy(LinkedList points, double speed) {
        Map<String, Object> parameters = new HashMap<>() {{
            put("path", points);
            put("speed", speed);
        }};
        try {
            return strategiesFactory.makeMovement("PathMovement", parameters);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}