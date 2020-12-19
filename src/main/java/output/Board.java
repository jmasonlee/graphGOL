package output;

import cell.Cell;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Board {
  private int upperYValue;
  private int lowerYValue;
  private int leftmostXValue;
  private int rightmostXValue;

  public Board(List<Cell> cellsForBoard) {
    calculateBoardBounds(cellsForBoard);
  }

  public int getWidth() {
    return Math.abs(leftmostXValue - rightmostXValue);
  }

  public int getHeight() {
    return Math.abs(upperYValue - lowerYValue);
  }

  private void calculateBoardBounds(List<Cell> cellsForBoard) {
    upperYValue = Collections.min(cellsForBoard, Comparator.comparing(cell -> cell.y)).y;
    lowerYValue = Collections.max(cellsForBoard, Comparator.comparing(cell -> cell.y)).y;
    leftmostXValue = Collections.min(cellsForBoard, Comparator.comparing(cell -> cell.x)).x;
    rightmostXValue = Collections.max(cellsForBoard, Comparator.comparing(cell -> cell.x)).x;
  }

}
