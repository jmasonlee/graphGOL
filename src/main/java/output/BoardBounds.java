package output;

import cell.Cell;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class BoardBounds {
  private int upperYValue = 5;
  private int leftmostXValue = 0;
  private int height = 5;
  private int width = 5;

  public BoardBounds(List<Cell> cellsForBoard) {
    calculateBoardBounds(cellsForBoard);
  }

  public int getWidth() {
    return width;
  }

  public int getHeight() {
    return height;
  }

  public int getUpperYValue() {
    return upperYValue;
  }

  public int getLeftmostXValue() {
    return leftmostXValue;
  }

  private void calculateBoardBounds(List<Cell> cellsForBoard) {
    if (cellsForBoard.isEmpty()) {
      return;
    }

    upperYValue = Collections.max(cellsForBoard, Comparator.comparing(cell -> cell.y)).y;
    int lowerYValue = Collections.min(cellsForBoard, Comparator.comparing(cell -> cell.y)).y;
    height =
        Math.abs(upperYValue - lowerYValue) > 5 ? Math.abs(upperYValue - lowerYValue) : this.height;

    leftmostXValue = Collections.min(cellsForBoard, Comparator.comparing(cell -> cell.x)).x;
    int rightmostXValue = Collections.max(cellsForBoard, Comparator.comparing(cell -> cell.x)).x;
    width =
        Math.abs(leftmostXValue - rightmostXValue) > 5
            ? Math.abs(leftmostXValue - rightmostXValue)
            : this.width;
  }
}
