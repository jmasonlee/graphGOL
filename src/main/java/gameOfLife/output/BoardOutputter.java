package gameOfLife.output;

import gameOfLife.cell.Cell;

import java.util.ArrayList;
import java.util.List;

public class BoardOutputter {

  public static String createBoardOutput(List<Cell> cells) {
    BoardBounds boardBounds = new BoardBounds(cells);
    List<String> emptyBoard = emptyBoardOfSize(boardBounds);
    return String.join("", LiveCellDrawer.drawLiveCellsOnBoard(emptyBoard, cells, boardBounds));
  }

  static List<String> emptyBoardOfSize(BoardBounds boardBounds) {
    List<String> board = new ArrayList<>();

    Coordinates coordinates = new Coordinates(boardBounds);

    board.addAll(HeaderDrawer.drawHeaderRow(coordinates));
    board.addAll(RowDrawer.drawEmptyRow(coordinates));

    return board;
  }
}