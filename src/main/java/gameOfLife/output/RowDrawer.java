package gameOfLife.output;

import gameOfLife.cell.Cell;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RowDrawer {
  static List<String> drawRows(Coordinates coordinates) {
    return drawRows(coordinates, new ArrayList<>());
  }

  static List<String> drawRows(Coordinates coordinates, List<Cell> liveCells) {
    List<String> board = new ArrayList<>();
    int boardWidth = coordinates.xCoordinates.size();

    coordinates.yCoordinates.forEach(
        yCoordinate -> {
          drawCoordinateSquare(board, yCoordinate);
          board.addAll(Collections.nCopies(boardWidth, BoardSquare.EMPTY_CELL.getValue()));
          board.add(BoardSquare.ROW_END.getValue());
        });

    return board;
  }

  private static void drawCoordinateSquare(List<String> board, String yCoordinate) {
    if (yCoordinate.length() == 1) {
      board.add(BoardSquare.SPACE.getValue() + yCoordinate);
    } else {
      board.add(yCoordinate);
    }
  }
}