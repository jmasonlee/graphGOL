package gameOfLife.TestUtils;

import gameOfLife.cell.Cell;

import java.util.ArrayList;
import java.util.List;

public class CellCoverage {
  public static List<Cell> generateAllPossibleCellsBetweenTwoCells(Cell upperLeftCell, Cell lowerRightCell) {
    List<Cell> cells = new ArrayList<>();

    for (int x = upperLeftCell.x; x < lowerRightCell.x; x++) {
      for (int y = lowerRightCell.y; y < upperLeftCell.y; y++) {
        cells.add(new Cell(x,y));
      }
    }

    return cells;
  }
}
