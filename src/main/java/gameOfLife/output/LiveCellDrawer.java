package gameOfLife.output;

import gameOfLife.cell.Cell;

import java.util.List;

public class LiveCellDrawer {
  static List<String> drawLiveCellsOnBoard(List<String> oldBoard, List<Cell> cells, BoardBounds boardBounds) {
    cells.forEach(c -> {
      int positionInBoard = getCellPositionInBoard(c, boardBounds);
      oldBoard.set(positionInBoard, BoardSquare.LIVE_CELL.getValue());
    });

    return oldBoard;
  }

  private static int getCellPositionInBoard(Cell cell, BoardBounds boardBounds) {
    int adjustedX = Math.abs(boardBounds.getLeftmostXValue() - cell.x);
    int adjustedY = Math.abs(boardBounds.getUpperYValue() - cell.y);

    int boardWidth = 2 + boardBounds.getWidth();

    return boardWidth + (adjustedY * boardWidth) + (adjustedX + 1);
  }
}