package gameOfLife.output;

import java.util.List;

public class CoordinateSquareDrawer {
  protected static String drawCoordinateSquare(List<String> board, String yCoordinate) {
    String coordinateSquare = "";
    if (yCoordinate.length() == 1) {
      coordinateSquare = BoardSquare.SPACE.getValue() ;
    }

    coordinateSquare += yCoordinate;

    return coordinateSquare;
  }
}
