package gameOfLife.output;

import gameOfLife.cell.Cell;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

public class BoardBounds {
  public static final int DEFAULT_BOARD_SIZE = 5;
  private int upperYValue = 5;
  private int leftmostXValue = 0;
  private int height = DEFAULT_BOARD_SIZE;
  private int width = DEFAULT_BOARD_SIZE;

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

    Function<Cell, Integer> fetchYvalue = c->c.y;
    upperYValue = getCellWithMaxCoordinate(cellsForBoard, fetchYvalue).y;
    int lowerYValue = getCellWithLowestCoordinate(cellsForBoard, fetchYvalue).y;
    height = getDimension(lowerYValue, upperYValue);

    Function<Cell, Integer> fetchXvalue = c->c.x;
    leftmostXValue = getCellWithLowestCoordinate(cellsForBoard, fetchXvalue).x;
    int rightmostXValue = getCellWithMaxCoordinate(cellsForBoard, fetchXvalue).x;
    width = getDimension(rightmostXValue, leftmostXValue);
  }

  private Cell getCellWithLowestCoordinate(List<Cell> cellsForBoard, Function<Cell, Integer> fetchCoordinate) {
    return Collections.min(cellsForBoard, Comparator.comparing(fetchCoordinate));
  }

  private Cell getCellWithMaxCoordinate(List<Cell> cellsForBoard, Function<Cell, Integer> fetchCoordinate) {
    return Collections.max(cellsForBoard, Comparator.comparing(fetchCoordinate));
  }

  private int getDimension(int lowestDimensionValue, int highestDimensionValue) {
    return Math.abs(highestDimensionValue - lowestDimensionValue) < DEFAULT_BOARD_SIZE ?
        DEFAULT_BOARD_SIZE :
        calculateDimensionLargerThanDefault(lowestDimensionValue, highestDimensionValue);
  }

  private int calculateDimensionLargerThanDefault(int lowestDimensionValue, int highestDimensionValue) {
    return Math.abs(highestDimensionValue - lowestDimensionValue) + 1;
  }

}
