package gameOfLife.output;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RowDrawer {
  static List<String> drawRows(Coordinates coordinates) {
    List<String> board = new ArrayList<>();
    int boardWidth = coordinates.xCoordinates.size();

    coordinates.yCoordinates.forEach(
        yCoordinate -> {
          board.add(yCoordinate);
          board.addAll(Collections.nCopies(boardWidth, BoardSquare.EMPTY_CELL.getValue()));
          board.add(BoardSquare.ROW_END.getValue());
        });

    return board;
  }
}