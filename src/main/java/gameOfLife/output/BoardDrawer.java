package gameOfLife.output;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class BoardDrawer {

  static List<String> drawHeaderRow(Coordinates coordinates) {
    List<String> board = new ArrayList<>();

    board.add(String.join("", Collections.nCopies(coordinates.widestCoordinateString, BoardSquare.SPACE.getValue())));
    board.addAll(addSpacingToColumnHeaders(coordinates.xCoordinates));
    board.add(BoardSquare.TOP_BOUNDARY_END.getValue());

    return board;
  }

  private static List<String> addSpacingToColumnHeaders(List<String> headers) {
    return headers.stream().map(c -> BoardSquare.SPACE.getValue() + c).collect(Collectors.toList());
  }

}
