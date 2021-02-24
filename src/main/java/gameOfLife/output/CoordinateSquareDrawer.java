package gameOfLife.output;

import java.util.List;

public class CoordinateSquareDrawer {
  protected static void drawCoordinateSquare(List<String> board, String yCoordinate) {
    if (yCoordinate.length() == 1) {
      board.add(BoardSquare.SPACE.getValue() + yCoordinate);
    } else {
      board.add(yCoordinate);
    }
  }
}
