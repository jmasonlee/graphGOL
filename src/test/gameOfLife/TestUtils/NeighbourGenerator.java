package gameOfLife.TestUtils;

import gameOfLife.LiveCellsGraphTest;
import gameOfLife.cell.Cell;
import org.apache.commons.math3.util.CombinatoricsUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class NeighbourGenerator {
  public static List<List<Cell>> allCombinationsOfNeighbours(List<Cell> allNeighbours, int desiredNumberOfNeighbours) {

    Iterator<int[]> neighbourIterator =
      createCombinationsIterator(allNeighbours, desiredNumberOfNeighbours);

    return iterateOverAllPossibleCombinations(allNeighbours, neighbourIterator);
  }

  private static List<List<Cell>> iterateOverAllPossibleCombinations(
    List<Cell> allNeighbours, Iterator<int[]> neighbourIterator) {
    List<List<Cell>> neighbourCombinations = new ArrayList<>();

    while (neighbourIterator.hasNext()) {
      int[] neighbourIndices = neighbourIterator.next();

      List<Cell> neighbours = matchIndicesToNeighbours(allNeighbours, neighbourIndices);

      neighbourCombinations.add(neighbours);
    }

    return neighbourCombinations;
  }

  private static List<Cell> matchIndicesToNeighbours(List<Cell> allNeighbours, int[] neighbourIndices) {
    List<Cell> neighbours = new ArrayList<>();

    for (int index : neighbourIndices) {
      neighbours.add(allNeighbours.get(index));
    }

    return neighbours;
  }

  private static Iterator<int[]> createCombinationsIterator(
    List<Cell> allNeighbours, int desiredNumberOfNeighbours) {
    return CombinatoricsUtils.combinationsIterator(allNeighbours.size(), desiredNumberOfNeighbours);
  }

  public static List<Cell> generateAllPossibleNeighboursForCell(Cell cell) {
    Cell lowerLeftCell = new Cell(-1, -1);
    Cell upperRightCell = new Cell(1,1);

    lowerLeftCell.x = cell.x + lowerLeftCell.x;
    lowerLeftCell.y = cell.y + lowerLeftCell.y;

    upperRightCell.x = cell.x + upperRightCell.x;
    upperRightCell.y = cell.y + upperRightCell.y;

    List<Cell> allNeighbours = CellCoverage.generateAllPossibleCellsBetweenTwoCells(lowerLeftCell,upperRightCell);
    allNeighbours.remove(cell);

    return allNeighbours;
  }
}
