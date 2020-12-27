package gameOfLife;

import gameOfLife.cell.Cell;
import org.approvaltests.Approvals;
import org.approvaltests.combinations.CombinationApprovals;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class LiveCellsGraphTest {

  @Test
  public void testCanGetCellsWithNoNeighbours() {
    List<Cell> cellWithNoNeighbours = Arrays.asList(new Cell[]{new Cell(0, 0)});

    LiveCellsGraph graph = new LiveCellsGraph(cellWithNoNeighbours);

    Approvals.verify(cellWithNoNeighbours.toString() + " => " + graph.getCellsWithNumberOfNeighbours(0));
  }

  @Test
  public void testCanGetCellsWithOneNeighbour() {
    Cell[] centre = new Cell[]{new Cell(0, 0)};
    Cell[] possibleNeighbours = new Cell[8];
    generateAllPossibleNeighboursForCell(centre[0]).toArray(possibleNeighbours);

    //testOutput.append("ONE NEIGHBOUR:\n");

    CombinationApprovals.verifyAllCombinations(this::findCellsWithSpecifiedNumberOfNeighbours, centre, possibleNeighbours);

//    testOutput.append("\nTWO NEIGHBOURS:\n");
//    List<Cell> cells = new ArrayList<>();
//    cells.add(new Cell(0, 0));
//    cells.add(new Cell(0, 1));
//    cells.add(new Cell(0, -1));
//    LiveCellsGraph graph = new LiveCellsGraph(cells);
//    testOutput.append(cells);
//    testOutput.append(" => ");
//    testOutput.append(graph.getCellsWithNumberOfNeighbours(2));
//    Approvals.verify(testOutput);
  }

  private String findCellsWithSpecifiedNumberOfNeighbours(Cell centre, Cell neighbour) {
    StringBuilder testOutput = new StringBuilder();

    List<Cell> cells = new ArrayList<>();
    cells.add(centre);
    cells.add(neighbour);

    LiveCellsGraph graph = new LiveCellsGraph(cells);

    testOutput.append(graph.getCellsWithNumberOfNeighbours(1));
    testOutput.append("\n");

    return testOutput.toString();
  }


  private List<Cell> generateAllPossibleNeighboursForCell(Cell cell) {
    Integer[] neighbourRange = new Integer[] {-1, 0, 1};
    List<Cell> possibleNeighbours = new ArrayList<>();
    for (int x = 0; x < neighbourRange.length; x++) {
      for (int y = 0; y < neighbourRange.length; y++) {
        if (neighbourRange[x] == 0 && neighbourRange[y] == 0) {
          continue;
        }

        int neighbourX = cell.x+neighbourRange[x];
        int neighbourY = cell.y+neighbourRange[y];

        possibleNeighbours.add(new Cell(neighbourX, neighbourY));
      }
    }

    return possibleNeighbours;
  }

  private Cell calculateCentreBasedOnXandY(Integer x, Integer y) {
    int initialX = 0;
    int centreSeparator = 6;
    int gridNumber = x * 3 + y;

    return new Cell(initialX + (centreSeparator * gridNumber), 0);
  }

  @Test
  public void testCanGetCellsWithTwoNeighbours() {
    List<Cell> cells = new ArrayList<>();
    cells.add(new Cell(0, 0));
    cells.add(new Cell(0, 1));
    cells.add(new Cell(0, -1));

    List<Cell> expectedOutput = new ArrayList<>();
    expectedOutput.add(cells.get(0));

    LiveCellsGraph liveCells = new LiveCellsGraph(cells);
    assertEquals(expectedOutput, liveCells.getCellsWithNumberOfNeighbours(2));
  }
}
