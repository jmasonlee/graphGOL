package output;

import cell.Cell;
import org.apache.commons.lang.StringUtils;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class BoardOutputter {

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
    return (adjustedY * boardBounds.getWidth()) + adjustedY + adjustedX;
  }

  private static StringBuilder emptyBoardOfSize(int width, int height) {
    StringBuilder board = new StringBuilder();

    for (int i = 0; i < height; i++) {
      board.append(StringUtils.repeat(" ", width));
      if (i < height - 1) {
        board.append("\n");
      }
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

      upperYValue = Collections.max(cellsForBoard, Comparator.comparing(cell -> cell.y)).y;
      int lowerYValue = Collections.min(cellsForBoard, Comparator.comparing(cell -> cell.y)).y;
      height =
          getDimension(lowerYValue, upperYValue, this.height);

      leftmostXValue = Collections.min(cellsForBoard, Comparator.comparing(cell -> cell.x)).x;
      int rightmostXValue = Collections.max(cellsForBoard, Comparator.comparing(cell -> cell.x)).x;
      width =
          getDimension(rightmostXValue, leftmostXValue, this.width);
    }

    private int getDimension(int lowestDimensionValue, int highestDimensionValue, int p) {
      return Math.abs(highestDimensionValue - lowestDimensionValue) < DEFAULT_BOARD_SIZE ?
          p :
          Math.abs(highestDimensionValue - lowestDimensionValue) + 1;
    }

  }
}