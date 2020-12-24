package gameOfLife;

import cell.Cell;

import java.util.ArrayList;
import java.util.List;

public class TestHelper {
  public List<Cell> getListOfCellsToClassify(Cell cell) {
    int maxOneDimensionalDistance = 3;
    List<Cell> cellsToClassify = new ArrayList<Cell>();

    for (int x = cell.x - maxOneDimensionalDistance; x <= cell.x + maxOneDimensionalDistance; x++) {
      for (int y = cell.y - maxOneDimensionalDistance;
          y <= cell.y + maxOneDimensionalDistance;
          y++) {
        cellsToClassify.add(new Cell(x, y));
      }
    }

    return cellsToClassify;
  }
}

