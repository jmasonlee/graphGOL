package gameOfLife.output;

import gameOfLife.cell.Cell;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

    Coordinates coordinates = new Coordinates(boardBounds);

    board.addAll(createHeaderRow(coordinates.xCoordinates, coordinates.widestCoordinateString));
    board.addAll(createEmptyRows(boardBounds, coordinates.yCoordinates));

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

    for (int i = 0; i < boardBounds.getHeight(); i++) {
      board.add(yCoords.get(i));
      board.addAll(Collections.nCopies(boardBounds.getWidth(), CELL));
      board.add(ROW_END);
    }

    return board;
  }

}