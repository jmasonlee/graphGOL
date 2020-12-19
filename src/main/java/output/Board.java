package output;

import cell.Cell;
import org.apache.commons.lang.StringUtils;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Board {
  private StringBuilder board;
  private int upperYValue = 5;
  private int lowerYValue = 0;
  private int leftmostXValue = 0;
  private int rightmostXValue = 5;

  public Board(List<Cell> cellsForBoard) {
    calculateBoardBounds(cellsForBoard);
    createEmptyBoard();
  }

  public int getWidth() {
    return Math.abs(leftmostXValue - rightmostXValue);
  }

  public int getHeight() {
    return Math.abs(upperYValue - lowerYValue);
  }

  @Override
  public String toString() {
    return board.toString();
  }

  private void createEmptyBoard() {
    board = new StringBuilder();

    for (int i = 0; i < getHeight(); i++) {
      board.append(StringUtils.repeat(" ", getWidth()));
      if (i < getHeight() - 1) {
        board.append("\n");
      }
    }
  }

  private void calculateBoardBounds(List<Cell> cellsForBoard) {
    if (cellsForBoard.isEmpty()) {
      return;
    }

    upperYValue = Collections.min(cellsForBoard, Comparator.comparing(cell -> cell.y)).y;
    lowerYValue = Collections.max(cellsForBoard, Comparator.comparing(cell -> cell.y)).y;
    leftmostXValue = Collections.min(cellsForBoard, Comparator.comparing(cell -> cell.x)).x;
    rightmostXValue = Collections.max(cellsForBoard, Comparator.comparing(cell -> cell.x)).x;
  }
}
