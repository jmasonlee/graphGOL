package gameOfLife.output;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class HeaderDrawer {

  static List<String> drawHeaderRow(Coordinates coordinates) {
    List<String> board = new ArrayList<>();
    int squareWidth = Math.max(coordinates.widestCoordinateString, BoardSquare.EMPTY_CELL.MIN_WIDTH);

    board.add(String.join("", Collections.nCopies(squareWidth, BoardSquare.SPACE.getValue())));
    board.addAll(addSpacingToColumnHeaders(coordinates.xCoordinates));
    board.add(BoardSquare.TOP_BOUNDARY_END.getValue());

    return board;
  }

  private static List<String> addSpacingToColumnHeaders(List<String> headers) {
    return headers.stream().map(
        c -> c.length() == 1 ?
            BoardSquare.SPACE.getValue() + BoardSquare.SPACE.getValue() + c :
            BoardSquare.SPACE.getValue() + c
    ).collect(Collectors.toList());
  }

}
