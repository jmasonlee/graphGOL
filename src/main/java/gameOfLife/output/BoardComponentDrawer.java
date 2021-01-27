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

    board.addAll(createHeaderRowWithCoordinates(coordinates));
    board.addAll(createEmptyRowComponents(coordinates));

    return board;
  }

  private static List<String> createHeaderRowWithCoordinates(Coordinates coordinates) {
    List<String> board = new ArrayList<String>();

    board.add(" " + String.join("", Collections.nCopies(coordinates.widestCoordinateString, " ")));
    board.addAll(formatIndividualColumnHeaders(coordinates.xCoordinates));
    board.add(TOP_BOUNDARY_END);

    return board;
  }

  private static List<String> formatIndividualColumnHeaders(List<String> xCoords) {
    List<String> formattedCoords = new ArrayList<String>();
    formattedCoords.add(xCoords.get(0));

    for (int i = 1; i < xCoords.size(); i++) {
      formattedCoords.add(" " + xCoords.get(i));
    }

    return formattedCoords;
  }

  private static List<String> createEmptyRowComponents(Coordinates coordinates) {
    List<String> board = new ArrayList<String>();
    int boardWidth = coordinates.xCoordinates.size();

    for (int i = 0; i < coordinates.yCoordinates.size(); i++) {
      board.add(coordinates.yCoordinates.get(i));
      board.addAll(Collections.nCopies(boardWidth, CELL));
      board.add(ROW_END);
    }

    return board;
  }
}