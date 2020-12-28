package gameOfLife;

import gameOfLife.cell.Cell;
import org.apache.commons.math3.util.CombinatoricsUtils;
import org.approvaltests.Approvals;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class LiveCellsGraphTest {

  @Test
  public void testCanGetCellsWithNoNeighbours() {
    List<Cell> cellWithNoNeighbours = Arrays.asList(new Cell[] {new Cell(0, 0)});

    LiveCellsGraph graph = new LiveCellsGraph(cellWithNoNeighbours);

    Approvals.verify(
        cellWithNoNeighbours.toString() + " => " + graph.getCellsWithNumberOfNeighbours(0));
  }

  @Test
  public void testCanGetCellsWithOneNeighbour() {
    Cell centre = new Cell(0, 0);
    List<Cell> possibleNeighbours = generateAllPossibleNeighboursForCell(centre);
    int desiredNumberOfNeighbours = 1;

    List<List<Cell>> neighbourCombinations =
        allCombinationsOfDesiredNumberOfNeighbours(possibleNeighbours, desiredNumberOfNeighbours);

    StringBuilder testOutput =
        testFilteringOnNeighbourCountForAllCombinationsOfNeighbours(
            centre, desiredNumberOfNeighbours, neighbourCombinations);

    Approvals.verify(testOutput);
  }

  @Test
  public void testCanGetCellsWithTwoNeighbours() {
    Cell centre = new Cell(0, 0);
    int desiredNumberOfNeighbours = 2;
    List<Cell> possibleNeighbours = generateAllPossibleNeighboursForCell(centre);

    List<List<Cell>> neighbourCombinations =
        allCombinationsOfDesiredNumberOfNeighbours(possibleNeighbours, desiredNumberOfNeighbours);

    StringBuilder testOutput =
        testFilteringOnNeighbourCountForAllCombinationsOfNeighbours(
            centre, desiredNumberOfNeighbours, neighbourCombinations);

    Approvals.verify(testOutput);
  }

  private StringBuilder testFilteringOnNeighbourCountForAllCombinationsOfNeighbours(
      Cell centre, int desiredNumberOfNeighbours, List<List<Cell>> neighbourCombinations) {
    StringBuilder testOutput = new StringBuilder();

    neighbourCombinations.forEach(
        neighbourList -> {
          testOutput.append(
              findCellsWithSpecifiedNumberOfNeighbours(
                  centre, neighbourList, desiredNumberOfNeighbours));
        });

    return testOutput;
  }

  private List<List<Cell>> allCombinationsOfDesiredNumberOfNeighbours(
      List<Cell> possibleNeighbours, int desiredNumberOfNeighbours) {

    List<List<Cell>> neighbourCombinations = new ArrayList<>();
    Iterator<int[]> neighbourIterator =
        CombinatoricsUtils.combinationsIterator(
            possibleNeighbours.size(), desiredNumberOfNeighbours);

    while (neighbourIterator.hasNext()) {
      int[] combinationIndices = neighbourIterator.next();
      List<Cell> neighbours = new ArrayList<>();

      for (int index : combinationIndices) {
        neighbours.add(possibleNeighbours.get(index));
      }

      neighbourCombinations.add(neighbours);
    }

    return neighbourCombinations;
  }

  private String findCellsWithSpecifiedNumberOfNeighbours(
      Cell centre, List<Cell> neighbours, int desiredNumberOfNeighbours) {
    StringBuilder testOutput = new StringBuilder();

    LiveCellsGraph graph = new LiveCellsGraph(getCellsUnderTest(centre, neighbours));

    testOutput.append(graph.getCells());
    testOutput.append(" => ");
    testOutput.append(graph.getCellsWithNumberOfNeighbours(desiredNumberOfNeighbours));
    testOutput.append("\n");

    return testOutput.toString();
  }

  private List<Cell> getCellsUnderTest(Cell centre, List<Cell> neighbours) {
    List<Cell> cells = new ArrayList<>();
    cells.add(centre);
    cells.addAll(neighbours);
    return cells;
  }

  private List<Cell> generateAllPossibleNeighboursForCell(Cell cell) {
    Integer[] neighbourRange = new Integer[] {-1, 0, 1};
    List<Cell> possibleNeighbours = new ArrayList<>();

    for (int x = 0; x < neighbourRange.length; x++) {
      for (int y = 0; y < neighbourRange.length; y++) {
        if (neighbourRange[x] == 0 && neighbourRange[y] == 0) {
          continue;
        }

        possibleNeighbours.add(createNeighbour(cell, neighbourRange[x], neighbourRange[y]));
      }
    }

    return possibleNeighbours;
  }

  private Cell createNeighbour(Cell centre, int relativeX, int relativeY) {
    int neighbourX = centre.x + relativeX;
    int neighbourY = centre.y + relativeY;

    return new Cell(neighbourX, neighbourY);
  }

  private Cell calculateCentreBasedOnXandY(Integer x, Integer y) {
    int initialX = 0;
    int centreSeparator = 6;
    int gridNumber = x * 3 + y;

    return new Cell(initialX + (centreSeparator * gridNumber), 0);
  }
}
