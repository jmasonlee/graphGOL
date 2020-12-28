package gameOfLife;

import gameOfLife.cell.Cell;
import org.apache.commons.math3.util.CombinatoricsUtils;
import org.approvaltests.Approvals;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

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
    List<Cell> allNeighbours = generateAllPossibleNeighboursForCell(centre);
    int desiredNumberOfNeighbours = 1;

    List<List<Cell>> neighbourCombinations =
        allCombinationsOfNeighbours(allNeighbours, desiredNumberOfNeighbours);

    StringBuilder testOutput =
        filterOnNeighbourCountForAllNeighbourCombinations(
            centre, desiredNumberOfNeighbours, neighbourCombinations);

    Approvals.verify(testOutput);
  }

  @Test
  public void testCanGetCellsWithTwoNeighbours() {
    Cell centre = new Cell(0, 0);
    int desiredNumberOfNeighbours = 2;
    List<Cell> allNeighbours = generateAllPossibleNeighboursForCell(centre);

    List<List<Cell>> neighbourCombinations =
        allCombinationsOfNeighbours(allNeighbours, desiredNumberOfNeighbours);

    StringBuilder testOutput =
        filterOnNeighbourCountForAllNeighbourCombinations(
            centre, desiredNumberOfNeighbours, neighbourCombinations);

    Approvals.verify(testOutput);
  }

  private StringBuilder filterOnNeighbourCountForAllNeighbourCombinations(
      Cell centre, int desiredNumberOfNeighbours, List<List<Cell>> neighbourCombinations) {
    StringBuilder testOutput = new StringBuilder();

    neighbourCombinations.forEach(
        neighbourList -> {
          testOutput.append(
              filterCellsOnNeighbourCount(
                  centre, neighbourList, desiredNumberOfNeighbours));
        });

    return testOutput;
  }

  private List<List<Cell>> allCombinationsOfNeighbours(
      List<Cell> allNeighbours, int desiredNumberOfNeighbours) {

    Iterator<int[]> neighbourIterator =
        createCombinationsIterator(allNeighbours, desiredNumberOfNeighbours);

    return iterateOverAllPossibleCombinations(allNeighbours, neighbourIterator);
  }

  private Iterator<int[]> createCombinationsIterator(
      List<Cell> allNeighbours, int desiredNumberOfNeighbours) {
    return CombinatoricsUtils.combinationsIterator(allNeighbours.size(), desiredNumberOfNeighbours);
  }

  private List<List<Cell>> iterateOverAllPossibleCombinations(
      List<Cell> allNeighbours, Iterator<int[]> neighbourIterator) {
    List<List<Cell>> neighbourCombinations = new ArrayList<>();

    while (neighbourIterator.hasNext()) {
      int[] neighbourIndices = neighbourIterator.next();

      List<Cell> neighbours = matchIndicesToNeighbours(allNeighbours, neighbourIndices);

      neighbourCombinations.add(neighbours);
    }

    return neighbourCombinations;
  }

  private List<Cell> matchIndicesToNeighbours(List<Cell> allNeighbours, int[] neighbourIndices) {
    List<Cell> neighbours = new ArrayList<>();

    for (int index : neighbourIndices) {
      neighbours.add(allNeighbours.get(index));
    }

    return neighbours;
  }

  private String filterCellsOnNeighbourCount(
      Cell centre, List<Cell> neighbours, int desiredNumberOfNeighbours) {

    LiveCellsGraph graph = new LiveCellsGraph(getCellsUnderTest(centre, neighbours));
    StringBuilder testOutput =
        getOutputFromFilteringBasedOnNumberOfNeighbours(desiredNumberOfNeighbours, graph);

    return testOutput.toString();
  }

  private StringBuilder getOutputFromFilteringBasedOnNumberOfNeighbours(
      int desiredNumberOfNeighbours, LiveCellsGraph graph) {
    StringBuilder testOutput = new StringBuilder();

    testOutput.append(graph.getCells());
    testOutput.append(" => ");
    testOutput.append(graph.getCellsWithNumberOfNeighbours(desiredNumberOfNeighbours));
    testOutput.append("\n");

    return testOutput;
  }

  private List<Cell> getCellsUnderTest(Cell centre, List<Cell> neighbours) {
    List<Cell> cells = new ArrayList<>();
    cells.add(centre);
    cells.addAll(neighbours);
    return cells;
  }

  private List<Cell> generateAllPossibleNeighboursForCell(Cell cell) {
    Integer[] neighbourRange = new Integer[] {-1, 0, 1};
    List<Cell> allNeighbours = new ArrayList<>();

    for (int x = 0; x < neighbourRange.length; x++) {
      for (int y = 0; y < neighbourRange.length; y++) {
        if (neighbourRange[x] == 0 && neighbourRange[y] == 0) {
          continue;
        }

        allNeighbours.add(createNeighbour(cell, neighbourRange[x], neighbourRange[y]));
      }
    }

    return allNeighbours;
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
