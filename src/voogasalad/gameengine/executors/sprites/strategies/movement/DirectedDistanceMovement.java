package voogasalad.gameengine.executors.sprites.strategies.movement;

import voogasalad.gameengine.executors.exceptions.GameEngineException;
import voogasalad.gameengine.executors.objectcreators.MovementBuilder;

import java.awt.geom.Point2D;
import java.util.List;

public class DirectedDistanceMovement implements MovementStrategy{

    private MovementBuilder myMovementBuilder;
    private double myAngle;
    private double myDistance;
    private double mySpeed;

    public DirectedDistanceMovement(MovementBuilder builder) {
        myMovementBuilder = builder;
        //TODO: Get angle, distance and speed through MovementBuilder object passed to constructor
        myAngle = 0;
        myDistance = builder.getDistance();
        mySpeed = builder.getSpeed();
    }

    @Override
    public void updateDirectionalAngle(double angle) {
        myAngle = angle;
    }

    @Override
    public MovementStrategy makeClone() {
        return new DirectedDistanceMovement(myMovementBuilder);
    }

    @Override
    public Point2D.Double calculateNextPosition(double elapsedTime, Point2D.Double currentPosition) {
        Point2D.Double oldPosition = currentPosition;
        currentPosition.setLocation(currentPosition.getX() + currentPosition.getX() * Math.cos(myAngle)*elapsedTime*mySpeed, currentPosition.getY() + currentPosition.getY()*Math.sin(myAngle)*elapsedTime*mySpeed);
//        double distance = calculateDistanceTravelled(oldPosition, currentPosition);
        return currentPosition;
    }

//    private double calculateDistanceTravelled(Point2D.Double oldPosition, Point2D.Double currentPosition){
//        double distance = 0;
//        distance = Math.sqrt(Math.pow(o))
//        return distance;
//    }

    @Override
    public void updatePath(List<Point2D.Double> path) {
        //do nothing
    }
}
