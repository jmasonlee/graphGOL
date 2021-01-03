package gameOfLife.output;

import gameOfLife.cell.Cell;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BoardOutputter {

  private static final String TOP_BOUNDARY = " __";
  private static final String TOP_BOUNDARY_END = " \n";
  private static final String CELL = "|__";
  private static final String ROW_END = "|\n";
  private static final int SHIFT = 3;

  public static String createBoardOutput(List<Cell> cells) {
    BoardBounds boardBounds = new BoardBounds(cells);
    StringBuilder emptyBoard = emptyBoardOfSize(boardBounds);
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

    int boardWidth = 2 + (CELL.length() * boardBounds.getWidth()) + ROW_END.length();

    return (boardWidth + SHIFT) + (adjustedY * boardWidth) + (adjustedX * CELL.length());
  }

  private static StringBuilder emptyBoardOfSize(BoardBounds boardBounds) {
    StringBuilder board = new StringBuilder();
    List<String> yCoords = createYCoordinates(boardBounds.upperYValue, boardBounds.height);
    List<String> xCoords = createXCoordinates(boardBounds.leftmostXValue, boardBounds.width);

    board.append(String.join("", createTopRow(xCoords)));
    board.append(createEmptyCells(boardBounds,yCoords));

    return board;
  }

  private static List<String> createTopRow(List<String> xCoords) {
    List<String > board = new ArrayList<>();

    board.add("   ");
    board.add(String.join(" ",xCoords));
    board.add(TOP_BOUNDARY_END);

    return board;
  }

  private static StringBuilder createEmptyCells(BoardBounds boardBounds, List<String> yCoords) {
    List<String> board = new ArrayList<>();

    for (int i = 0; i < boardBounds.height; i++) {
      board.add(yCoords.get(i));
      board.add(StringUtils.repeat(CELL, boardBounds.width));
      board.add(ROW_END);
    }

    return new StringBuilder(String.join("", board));
  }

  private static List<String> createXCoordinates(int leftmostXValue, int width) {
    int endAt = leftmostXValue + width -1;
    return createCoordinates(leftmostXValue, endAt);
  }

  private static List<String> createYCoordinates(int upperYValue, int dimensionSize) {
    int startAt = upperYValue - dimensionSize + 1;

    List<String> coordinateNumbers = createCoordinates(startAt, upperYValue);

    Collections.reverse(coordinateNumbers);

    return coordinateNumbers;
  }

  private static List<String> createCoordinates(int startAt, int endAt) {
    return IntStream.rangeClosed(startAt, endAt).boxed()
      .map(coord -> coord < 0 ? coord.toString() : " " + coord.toString())
      .collect(Collectors.toList());
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
}