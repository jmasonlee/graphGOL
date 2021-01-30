package gameOfLife.output;

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
    board.addAll(createEmptyRowComponents(coordinates));

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

  private static List<String> createEmptyRowComponents(Coordinates coordinates) {
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
}
