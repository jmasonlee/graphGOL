package gameOfLife.output;

import gameOfLife.cell.Cell;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BoardOutputter {

  private static final String TOP_BOUNDARY_END = " \n";
  private static final String CELL = "|__";
  private static final String ROW_END = "|\n";

  public static String createBoardOutput(List<Cell> cells) {
    BoardBounds boardBounds = new BoardBounds(cells);
    List<String> emptyBoard = emptyBoardOfSize(boardBounds);
    return String.join("", populateBoardWithCells(emptyBoard, cells, boardBounds));
  }

  private static List<String> populateBoardWithCells(List<String> oldBoard, List<Cell> cells, BoardBounds boardBounds) {
    List<String> boardWithCells = oldBoard;
    cells.forEach(c -> {
      int positionInBoard = getCellPositionInBoard(c, boardBounds);
      boardWithCells.set(positionInBoard, "|X_");
    });

    return boardWithCells;
  }

  private static int getCellPositionInBoard(Cell cell, BoardBounds boardBounds) {
    int adjustedX = Math.abs(boardBounds.getLeftmostXValue() - cell.x);
    int adjustedY = Math.abs(boardBounds.getUpperYValue() - cell.y);

    int boardWidth = 2 + boardBounds.getWidth();

    return boardWidth + (adjustedY * boardWidth) + (adjustedX + 1);
  }

  private static List<String> emptyBoardOfSize(BoardBounds boardBounds) {
    List<String> board = new ArrayList<>();
    Coordinates yCoords = createYCoordinates(boardBounds.upperYValue, boardBounds.height);
    Coordinates xCoords = createXCoordinates(boardBounds.leftmostXValue, boardBounds.width);

    board.addAll(createHeaderRow(xCoords.coordinates, yCoords.widestCoordinateString));
    board.addAll(createEmptyRows(boardBounds, yCoords.coordinates));

    return board;
  }

  private static List<String> createHeaderRow(List<String> xCoords, Integer widestY) {
    List<String > board = new ArrayList<>();

    board.add(" " + String.join("", Collections.nCopies(widestY, " ")));
    board.addAll(formatIndividualColumnHeaders(xCoords));
    board.add(TOP_BOUNDARY_END);

    return board;
  }

  private static List<String> formatIndividualColumnHeaders(List<String> xCoords) {
    List<String> formattedCoords = new ArrayList<>();
    formattedCoords.add(xCoords.get(0));

    for (int i = 1; i < xCoords.size(); i++) {
      formattedCoords.add(" "+xCoords.get(i));
    }

    return formattedCoords;
  }

  private static List<String> createEmptyRows(BoardBounds boardBounds, List<String> yCoords) {
    List<String> board = new ArrayList<>();

    for (int i = 0; i < boardBounds.height; i++) {
      board.add(yCoords.get(i));
      board.addAll(Collections.nCopies(boardBounds.width, CELL));
      board.add(ROW_END);
    }

    return board;
  }

  private static Coordinates createXCoordinates(int leftmostXValue, int width) {
    int endAt = leftmostXValue + width -1;
    return new Coordinates(createCoordinates(leftmostXValue, endAt));
  }

  private static Coordinates createYCoordinates(int upperYValue, int dimensionSize) {
    int startAt = upperYValue - dimensionSize + 1;

    List<String> coordinateNumbers = createCoordinates(startAt, upperYValue);

    Collections.reverse(coordinateNumbers);

    Coordinates coordinates = new Coordinates(coordinateNumbers);

    return coordinates;
  }

  private static List<String> createCoordinates(int startAt, int endAt) {
    return IntStream.rangeClosed(startAt, endAt).boxed()
      .map(coord -> coord < 0 || 9 < coord ? coord.toString() : " " + coord.toString())
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