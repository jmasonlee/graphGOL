package gameOfLife.output;

public class CoordinateSquareDrawer {
  protected static String drawCoordinateSquare(String yCoordinate) {
    String coordinateSquare = "";
    if (yCoordinate.length() == 1) {
      coordinateSquare = BoardSquare.SPACE.getValue() ;
    }

    coordinateSquare += yCoordinate;

    return coordinateSquare;
  }
}
