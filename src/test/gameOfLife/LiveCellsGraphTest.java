package gameOfLife;

import gameOfLife.TestUtils.CellCoverage;
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
    int desiredNumberOfNeighbours = 0;
    StringBuilder testOutput = buildGraphAndFilterOnNeighbourCount(desiredNumberOfNeighbours);

    Approvals.verify(testOutput);
  }

  @Test
  public void testCanGetCellsWithOneNeighbour() {
    int desiredNumberOfNeighbours = 1;
    StringBuilder testOutput = buildGraphAndFilterOnNeighbourCount(desiredNumberOfNeighbours);

    Approvals.verify(testOutput);
  }

  @Test
  public void testCanGetCellsWithTwoNeighbours() {
    int desiredNumberOfNeighbours = 2;

    StringBuilder testOutput = buildGraphAndFilterOnNeighbourCount(desiredNumberOfNeighbours);

    Approvals.verify(testOutput);
  }

  @Test
  public void testCanGetCellsWithThreeNeighbours() {
    int desiredNumberOfNeighbours = 3;
    StringBuilder testOutput = buildGraphAndFilterOnNeighbourCount(desiredNumberOfNeighbours);

    Approvals.verify(testOutput);
  }

  private StringBuilder buildGraphAndFilterOnNeighbourCount(int desiredNumberOfNeighbours) {
    Cell centre = new Cell(0, 0);
    List<Cell> allNeighbours = generateAllPossibleNeighboursForCell(centre);

    List<List<Cell>> neighbourCombinations =
        allCombinationsOfNeighbours(allNeighbours, desiredNumberOfNeighbours);

    return filterOnNeighbourCountForAllNeighbourCombinations(
        centre, desiredNumberOfNeighbours, neighbourCombinations);
  }

  private List<Cell> generateAllPossibleNeighboursForCell(Cell cell) {
    Integer[] neighbourRange = new Integer[] {-1, 0, 1};

    Cell lowerLeftCell = new Cell(-1, -1);
    Cell upperRightCell = new Cell(1,1);
    List<Cell> allNeighbours = CellCoverage.generateAllPossibleCellsBetweenTwoCells(lowerLeftCell,upperRightCell);
    allNeighbours.remove(cell);

    return allNeighbours;
  }

  private Cell createNeighbour(Cell centre, int relativeX, int relativeY) {
    int neighbourX = centre.x + relativeX;
    int neighbourY = centre.y + relativeY;

    return new Cell(neighbourX, neighbourY);
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

  private StringBuilder filterOnNeighbourCountForAllNeighbourCombinations(
      Cell centre, int desiredNumberOfNeighbours, List<List<Cell>> neighbourCombinations) {
    StringBuilder testOutput = new StringBuilder();

    neighbourCombinations.forEach(
        neighbourList -> testOutput.append(
            filterCellsOnNeighbourCount(
                centre, neighbourList, desiredNumberOfNeighbours)));

    return testOutput;
  }

  private String filterCellsOnNeighbourCount(
      Cell centre, List<Cell> neighbours, int desiredNumberOfNeighbours) {

    LiveCellsGraph graph = new LiveCellsGraph(getCellsUnderTest(centre, neighbours));
    List<Cell> originalCells = graph.getCells();
    List<Cell> filteredCells = graph.filterCellsByNeighbourCount(desiredNumberOfNeighbours);

    StringBuilder testOutput =
        formatFilteringResults(originalCells, filteredCells);

    return testOutput.toString();
  }

  private StringBuilder formatFilteringResults(
    List<Cell> originalCells, List<Cell> filteredCells) {
    StringBuilder testOutput = new StringBuilder();

    testOutput.append(originalCells);
    testOutput.append(" => ");
    testOutput.append(filteredCells);
    testOutput.append("\n");

    return testOutput;
  }

  private List<Cell> getCellsUnderTest(Cell centre, List<Cell> neighbours) {
    List<Cell> cells = new ArrayList<>();
    cells.add(centre);
    cells.addAll(neighbours);
    return cells;
  }
}
