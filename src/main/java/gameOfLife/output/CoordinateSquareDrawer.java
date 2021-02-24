package gameOfLife.output;

public class CoordinateSquareDrawer {
  protected static String drawCoordinateSquare(String yCoordinate) {
    String coordinateSquare = yCoordinate;
    if (yCoordinate.length() == 1) {
      coordinateSquare = BoardSquare.SPACE.getValue() + coordinateSquare ;
    }
    return coordinateSquare;
  }
}
