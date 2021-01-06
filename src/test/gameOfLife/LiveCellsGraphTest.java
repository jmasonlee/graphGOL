package gameOfLife;

import gameOfLife.TestUtils.CellCoverage;
import gameOfLife.TestUtils.NeighbourGenerator;
import gameOfLife.cell.Cell;
import org.approvaltests.combinations.CombinationApprovals;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class LiveCellsGraphTest {

  @Test
  public void testCanGetCellsWithDesiredNumberOfNeighbours() {
    Integer[] desiredNumberOfNeighbours = IntStream.rangeClosed(0, 8).boxed().toArray(Integer[]::new);
    CombinationApprovals.verifyAllCombinations(this::buildGraphAndFilterOnNeighbourCount, desiredNumberOfNeighbours);
  }

  private StringBuilder buildGraphAndFilterOnNeighbourCount(int desiredNumberOfNeighbours) {
    Cell centre = new Cell(0, 0);
    List<Cell> allNeighbours = generateAllPossibleNeighboursForCell(centre);

    List<List<Cell>> neighbourCombinations =
        NeighbourGenerator.allCombinationsOfNeighbours(allNeighbours, desiredNumberOfNeighbours);

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

    testOutput.append(("\t"));
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
