package gameOfLife.TestUtils;

import gameOfLife.cell.Cell;

import java.util.ArrayList;
import java.util.List;

public class CellCoverage {
  public static List<Cell> generateAllPossibleCellsBetweenTwoCells(Cell lowerLeftCell, Cell upperRightCell) {
    List<Cell> cells = new ArrayList<>();

    for (int x = lowerLeftCell.x; x < upperRightCell.x; x++) {
      for (int y = lowerLeftCell.y; y < upperRightCell.y; y++) {
        cells.add(new Cell(x,y));
      }
    }

    return cells;
  }
}
