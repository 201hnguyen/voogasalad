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


    @Test
    public void testCalculatePosition () {
        Point expected = new Point(0, 10);
        LinkedList<Point> points = new LinkedList<>();
        Point origin = new Point(0, 0);
        points.add(new Point(0, 100));
        MovementStrategy toTest = makeMovementStrategy(points, 10);
        Point result = toTest.calculateNextPosition(1.00, origin);
        assertEquals(expected, result);
    }

    @Test
    public void testCalculatePositionComplex () {
        Point expected = new Point(94, 92);
        LinkedList<Point> points = new LinkedList<>();
        Point origin = new Point(100, 100);
        points.add(new Point(70, 60));
        MovementStrategy toTest = makeMovementStrategy(points, 10);
        Point result = toTest.calculateNextPosition(1.00, origin);
        assertEquals(expected, result);
    }

    @Test
    public void testNoNextPositionCalculatePosition () {
        Point expected = new Point(0, 0);
        LinkedList<Point> points = new LinkedList<>();
        MovementStrategy toTest = makeMovementStrategy(points, 10);
        Point origin = new Point(0, 0);
        Point result = toTest.calculateNextPosition(1.00, origin);
        assertEquals(expected, result);
    }

    @Test
    public void testCalculatePositionPastEnd () {
        Point expected = new Point(0, 100);
        LinkedList<Point> points = new LinkedList<>();
        Point origin = new Point(0, 0);
        points.add(new Point(0, 100));
        MovementStrategy toTest = makeMovementStrategy(points, 10);
        Point result = toTest.calculateNextPosition(100.0, origin);
        assertEquals(expected, result);
    }

    @Test
    public void testRepeatedUpdatePosition () {
        Point expected = new Point(1000, 1000);
        LinkedList<Point> points = new LinkedList<>();
        Point current = new Point(100, 100);
        points.add(new Point(1000, 1000));
        MovementStrategy toTest = makeMovementStrategy(points, 50);
        for(int i = 0; i < 500; i++) {
            current = toTest.calculateNextPosition(0.5, current);
        }
        assertEquals(expected, current);
    }

    @Test
    public void testDirectionChange () {
        Point expected = new Point(650, 700);
        Point current = new Point(700, 700);
        LinkedList<Point> points = new LinkedList<>();
        points.add(new Point(750, 750));
        points.add(new Point(700, 750));
        points.add(new Point(650, 700));
        MovementStrategy toTest = makeMovementStrategy(points, 50);
        for(int i = 0; i < 500; i++) {
            current = toTest.calculateNextPosition(0.5, current);
        }
        assertEquals(expected, current);
    }

    @Test
    public void testDirectionChangeAfterLines () {
        Point expected = new Point(100, 150);
        Point current = new Point(0, 0);
        LinkedList<Point> points = new LinkedList<>();
        points.add(new Point(0, 100));
        points.add(new Point(100, 150));
        MovementStrategy toTest = makeMovementStrategy(points, 5);
        for(int i = 0; i < 500; i++) {
            current = toTest.calculateNextPosition(0.1, current);
        }
        assertEquals(expected, current);
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