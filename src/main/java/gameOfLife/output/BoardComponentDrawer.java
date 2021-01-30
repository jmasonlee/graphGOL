package gameOfLife.output;

import gameOfLife.cell.Cell;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class BoardComponentDrawer {
  static final String TOP_BOUNDARY_END = " \n";
  static final String EMPTY_CELL = "|__";
  static final String ROW_END = "|\n";

  static List<String> emptyBoardOfSize(BoardBounds boardBounds) {
    List<String> board = new ArrayList<String>();

    Coordinates coordinates = new Coordinates(boardBounds);

    board.addAll(drawHeaderRow(coordinates));
    board.addAll(drawEmptyRow(coordinates));

    return board;
  }

  static List<String> drawHeaderRow(Coordinates coordinates) {
    List<String> board = new ArrayList<String>();

    board.add(" " + String.join("", Collections.nCopies(coordinates.widestCoordinateString, " ")));
    board.addAll(addSpacingToColumnHeaders(coordinates.xCoordinates));
    board.add(TOP_BOUNDARY_END);

    return board;
  }

  private static List<String> addSpacingToColumnHeaders(List<String> xCoords) {
    List<String> formattedCoords = new ArrayList<String>();
    formattedCoords.add(xCoords.get(0));
    formattedCoords.addAll(xCoords.stream().skip(1).map(c -> " " + c).collect(Collectors.toList()));

    return formattedCoords;
  }

  static List<String> drawEmptyRow(Coordinates coordinates) {
    List<String> board = new ArrayList<>();
    int boardWidth = coordinates.xCoordinates.size();

    coordinates.yCoordinates.forEach(
        yCoordinate -> {
          board.add(yCoordinate);
          board.addAll(Collections.nCopies(boardWidth, EMPTY_CELL));
          board.add(ROW_END);
        });

    return board;
  }

  static List<String> drawLiveCellsOnBoard(List<String> oldBoard, List<Cell> cells, BoardBounds boardBounds) {
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
}
