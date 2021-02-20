package gameOfLife.TestUtils;

import gameOfLife.cell.Cell;
import gameOfLife.output.BoardBounds;
import gameOfLife.output.Coordinates;

import java.util.ArrayList;
import java.util.List;

public class CellCoverage {
  public static List<Cell> generateAllPossibleCellsBetweenTwoCells(Cell lowerLeftCell, Cell upperRightCell) {
    List<Cell> cells = new ArrayList<>();

    for (int x = lowerLeftCell.x; x <= upperRightCell.x; x++) {
      for (int y = lowerLeftCell.y; y <= upperRightCell.y; y++) {
        cells.add(new Cell(x,y));
      }
    }

    return cells;
  }

  public static Coordinates createCoordinates(Cell cell1, Cell cell2) {
    List<Cell> cells = generateAllPossibleCellsBetweenTwoCells(cell1, cell2);
    BoardBounds boardBounds = new BoardBounds(cells);
    return new Coordinates(boardBounds);
  }
}
