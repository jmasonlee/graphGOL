package gameOfLife.output;

import gameOfLife.cell.Cell;

import java.util.List;

public class BoardOutputter {

  public static String createBoardOutput(List<Cell> cells) {
    BoardBounds boardBounds = new BoardBounds(cells);
    List<String> emptyBoard = BoardComponentDrawer.emptyBoardOfSize(boardBounds);
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

}