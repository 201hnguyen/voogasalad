package voogasalad.gameengine.executors.sprites.strategies.movement;

import voogasalad.gameengine.executors.exceptions.GameEngineException;
import voogasalad.gameengine.executors.objectcreators.MovementBuilder;

import java.awt.geom.Point2D;
import java.util.List;

public class DirectedDistanceMovement implements MovementStrategy{

    private MovementBuilder myMovementBuilder;
    private double myAngle;
    private double myDistance;

    public DirectedDistanceMovement(MovementBuilder builder) {
        myMovementBuilder = builder;
//        myAngle = builder.getAngle();
//        myDistance = builder.getDistance();
        myAngle = 0;
        myDistance = 100;
    }

    @Override
    public void updateDirectionalAngle(double angle) {

    }

    @Override
    public MovementStrategy makeClone() {
        return new DirectedDistanceMovement(myMovementBuilder);
    }

    @Override
    public Point2D.Double calculateNextPosition(double elapsedTime, Point2D.Double currentPosition) {
        return currentPosition;
    }

    @Override
    public void updatePath(List<Point2D.Double> path) {
        //do nothing
    }
}
