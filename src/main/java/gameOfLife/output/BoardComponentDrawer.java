package gameOfLife.output;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BoardComponentDrawer {
  static final String TOP_BOUNDARY_END = " \n";
  static final String CELL = "|__";
  static final String ROW_END = "|\n";

  static List<String> emptyBoardOfSize(BoardBounds boardBounds) {
    List<String> board = new ArrayList<String>();

    Coordinates coordinates = new Coordinates(boardBounds);

    board.addAll(createHeaderRow(coordinates.xCoordinates, coordinates.widestCoordinateString));
    board.addAll(createEmptyRows(boardBounds, coordinates.yCoordinates));

    return board;
  }

  static List<String> createHeaderRow(List<String> xCoords, Integer widestY) {
    List<String> board = new ArrayList<String>();

    board.add(" " + String.join("", Collections.nCopies(widestY, " ")));
    board.addAll(formatIndividualColumnHeaders(xCoords));
    board.add(TOP_BOUNDARY_END);

    return board;
  }

  static List<String> formatIndividualColumnHeaders(List<String> xCoords) {
    List<String> formattedCoords = new ArrayList<String>();
    formattedCoords.add(xCoords.get(0));

    for (int i = 1; i < xCoords.size(); i++) {
      formattedCoords.add(" " + xCoords.get(i));
    }

    return formattedCoords;
  }

  static List<String> createEmptyRows(BoardBounds boardBounds, List<String> yCoords) {
    List<String> board = new ArrayList<String>();

    for (int i = 0; i < boardBounds.getHeight(); i++) {
      board.add(yCoords.get(i));
      board.addAll(Collections.nCopies(boardBounds.getWidth(), CELL));
      board.add(ROW_END);
    }

    return board;
  }
}