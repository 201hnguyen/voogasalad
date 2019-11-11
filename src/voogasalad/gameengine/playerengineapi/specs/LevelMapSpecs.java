package voogasalad.gameengine.playerengineapi.specs;

public class LevelMapSpecs {

    private int myScreenWidth;
    private int myScreenHeight;
    private int myNumRows;
    private int myNumColumns;
    private int[] myMapEncodings;

    public LevelMapSpecs(int numRows, int numCols, int[] mapEncodings, int screenWidth, int screenHeight) {
        myNumRows = numRows;
        myNumColumns = numCols;
        myMapEncodings = mapEncodings;
        myScreenWidth = screenWidth;
        myScreenHeight = screenHeight;
    }

    public int getNumRows() {
        return myNumRows;
    }

    public int getNumColumns() {
        return myNumColumns;
    }

    public int[] getMapEncodings() {
        return myMapEncodings;
    }

    public int getScreenWidth() {
        return myScreenWidth;
    }

    public int getScreenHeight() {
        return myScreenHeight;
    }
}
