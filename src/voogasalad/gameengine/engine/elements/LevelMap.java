package voogasalad.gameengine.engine.elements;

import voogasalad.gameengine.playerengineapi.specs.LevelMapSpecs;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class LevelMap {

    private int myNumRows;
    private int myNumColumns;
    private int[] myMapEncoding;
    private int myMapWidthInPixels;
    private int myMapHeightInPixels;


    public LevelMap(LevelMapSpecs levelMapSpecs)  {
        System.out.print("Level map encodings: ");
        for (int encoding : levelMapSpecs.getMapEncodings()) {
            System.out.print(encoding + " ");
        }
        System.out.println();
        myNumRows = levelMapSpecs.getNumRows();
        myNumColumns = levelMapSpecs.getNumColumns();
        myMapEncoding= levelMapSpecs.getMapEncodings();
        myMapWidthInPixels = levelMapSpecs.getScreenWidth();
        myMapHeightInPixels = levelMapSpecs.getScreenHeight();
    }



    public List<Point> getCenterCoordinatesForGroundType(int groundType) {
        List<Point> centerCoordinatesForGroundType = new ArrayList<>();
        List<Integer> indicesMatchingGroundType = getIndicesForGroundType(groundType);
        for (Integer index : indicesMatchingGroundType) {
            centerCoordinatesForGroundType.add(calculateCenterPixelCoordinates(index));
        }
        return centerCoordinatesForGroundType;
    }

    private List<Integer> getIndicesForGroundType(int groundType) {
        List<Integer> groundTypeIndices = new ArrayList<>();
        for (int i=0; i<myMapEncoding.length; i++) {
            if(myMapEncoding[i] == groundType) {
                groundTypeIndices.add(i);
            }
        }
        return groundTypeIndices;
    }

    private int calculateRowFromIndex(int index) {
        return index / myNumColumns;
    }

    private int calculateColumnFromIndex(int index) {
        return index % myNumRows;
    }

    private int calculateCellWidthInPixels() {
        return myMapWidthInPixels/myNumColumns;
    }

    private int calculateCellHeightInPixels() {
        return myMapHeightInPixels/myNumRows;
    }

    private Point calculateCenterPixelCoordinates(int index) {
        int row = calculateRowFromIndex(index);
        int column = calculateColumnFromIndex(index);
        int cellWidth = calculateCellWidthInPixels();
        int cellHeight = calculateCellHeightInPixels();
        int myCenterX = (cellWidth * column) + (cellWidth / 2);
        int myCenterY = (cellHeight * row) + (cellHeight / 2);
        return new Point(myCenterX, myCenterY);
    }
}
