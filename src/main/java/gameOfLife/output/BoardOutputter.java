package gameOfLife.output;

import gameOfLife.cell.Cell;

import java.util.List;

public class BoardOutputter {

  public static String createBoardOutput(List<Cell> cells) {
    BoardBounds boardBounds = new BoardBounds(cells);
    List<String> emptyBoard = BoardComponentDrawer.emptyBoardOfSize(boardBounds);
    return String.join("", BoardComponentDrawer.drawLiveCellsOnBoard(emptyBoard, cells, boardBounds));
  }

}