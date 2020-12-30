package gameOfLife.output;

import gameOfLife.cell.Cell;
import org.apache.commons.lang.StringUtils;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

public class BoardOutputter {

  private static final String TOP_BOUNDARY = " __";

  public static String createBoardOutput(List<Cell> cells) {
    BoardBounds boardBounds = new BoardBounds(cells);
    StringBuilder emptyBoard = emptyBoardOfSize(boardBounds.getWidth(), boardBounds.getHeight());
    return populateBoardWithCells(emptyBoard, cells, boardBounds);
  }

  private static String populateBoardWithCells(StringBuilder board, List<Cell> cells, BoardBounds boardBounds) {
    cells.forEach(c -> {
      int positionInBoard = getCellPositionInBoard(c, boardBounds);
      board.setCharAt(positionInBoard, 'X');
    });
    return board.toString();
  }

  private static int getCellPositionInBoard(Cell cell, BoardBounds boardBounds) {
    int adjustedX = Math.abs(boardBounds.getLeftmostXValue() - cell.x);
    int adjustedY = Math.abs(boardBounds.getUpperYValue() - cell.y);

    int boardWidth = (3 * boardBounds.getWidth()) + 2;

    return boardWidth + (adjustedY * boardWidth) + (adjustedX * 3);
  }

  private static StringBuilder emptyBoardOfSize(int width, int height) {
    StringBuilder board = new StringBuilder();

    board.append(StringUtils.repeat(TOP_BOUNDARY, width));
    board.append("\n");

    for (int i = 0; i < height; i++) {
      board.append(StringUtils.repeat("|__", width));
      board.append("|\n");
    }

    return board;
  }

  public static class BoardBounds {
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
      upperYValue = getCellWithMaxDimension(cellsForBoard, fetchYvalue).y;
      int lowerYValue = getCellWithMinDimension(cellsForBoard, fetchYvalue).y;
      height = getDimension(lowerYValue, upperYValue);

      Function<Cell, Integer> fetchXvalue = c->c.x;
      leftmostXValue = getCellWithMinDimension(cellsForBoard, fetchXvalue).x;
      int rightmostXValue = getCellWithMaxDimension(cellsForBoard, fetchXvalue).x;
      width = getDimension(rightmostXValue, leftmostXValue);
    }

    private Cell getCellWithMinDimension(List<Cell> cellsForBoard, Function<Cell, Integer> fetchCellDimension) {
      return Collections.min(cellsForBoard, Comparator.comparing(c -> fetchCellDimension.apply(c)));
    }

    private Cell getCellWithMaxDimension(List<Cell> cellsForBoard, Function<Cell, Integer> fetchCellDimension) {
      return Collections.max(cellsForBoard, Comparator.comparing(c->fetchCellDimension.apply(c)));
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
}