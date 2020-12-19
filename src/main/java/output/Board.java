package output;

import cell.Cell;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Board {
  private int width;
  private int height;

  public Board(List<Cell> cellsForBoard) {
    calculateBoardBounds(cellsForBoard);
  }

  public int getWidth() {
    return width;
  }

  public int getHeight() {
    return height;
  }

  private void calculateBoardBounds(List<Cell> cellsForBoard) {
    int upperYValue = Collections.min(cellsForBoard, Comparator.comparing(cell -> cell.y)).y;
    int lowerYValue = Collections.max(cellsForBoard, Comparator.comparing(cell -> cell.y)).y;
    int leftmostXValue = Collections.min(cellsForBoard, Comparator.comparing(cell -> cell.x)).x;
    int rightmostXValue = Collections.max(cellsForBoard, Comparator.comparing(cell -> cell.x)).x;

    this.width = Math.abs(leftmostXValue - rightmostXValue);
    this.height =  Math.abs(upperYValue - lowerYValue);
  }

}
