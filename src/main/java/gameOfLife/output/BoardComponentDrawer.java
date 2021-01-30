package gameOfLife.output;

import gameOfLife.cell.Cell;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class BoardComponentDrawer {

  static List<String> drawHeaderRow(Coordinates coordinates) {
    List<String> board = new ArrayList<String>();

    board.add(String.join("", Collections.nCopies(coordinates.widestCoordinateString, BoardSquare.SPACE.getValue())));
    board.addAll(addSpacingToColumnHeaders(coordinates.xCoordinates));
    board.add(BoardSquare.TOP_BOUNDARY_END.getValue());

    return board;
  }

  private static List<String> addSpacingToColumnHeaders(List<String> headers) {
    return headers.stream().map(c -> BoardSquare.SPACE.getValue() + c).collect(Collectors.toList());
  }

  static List<String> drawEmptyRow(Coordinates coordinates) {
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

  static List<String> drawLiveCellsOnBoard(List<String> oldBoard, List<Cell> cells, BoardBounds boardBounds) {
    List<String> boardWithCells = oldBoard;
    cells.forEach(c -> {
      int positionInBoard = getCellPositionInBoard(c, boardBounds);
      boardWithCells.set(positionInBoard, BoardSquare.LIVE_CELL.getValue());
    });

    return boardWithCells;
  }

  private static int getCellPositionInBoard(Cell cell, BoardBounds boardBounds) {
    int adjustedX = Math.abs(boardBounds.getLeftmostXValue() - cell.x);
    int adjustedY = Math.abs(boardBounds.getUpperYValue() - cell.y);

    int boardWidth = 2 + boardBounds.getWidth();

    return boardWidth + (adjustedY * boardWidth) + (adjustedX + 1);
  }
}
