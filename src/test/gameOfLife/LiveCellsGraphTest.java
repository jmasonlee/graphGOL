package gameOfLife;

import gameOfLife.cell.Cell;
import org.approvaltests.Approvals;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class LiveCellsGraphTest {
  @Test
  public void testCanGetCellsWithSpecifiedNumberOfNeighbours() {
    StringBuilder testOutput = new StringBuilder();

    Cell centre = new Cell(0, 0);
    List<Cell> possibleNeighbours = generateAllPossibleNeighboursForCell(centre);

    testOutput.append("ONE NEIGHBOUR:\n");

    for(int i = 0; i< possibleNeighbours.size(); i++) {
      testOutput = findCellsWithSpecifiedNumberOfNeighbours(testOutput, centre, possibleNeighbours.get(i));
    }

    testOutput.append("\nTWO NEIGHBOURS:\n");
    List<Cell> cells = new ArrayList<>();
    cells.add(new Cell(0, 0));
    cells.add(new Cell(0, 1));
    cells.add(new Cell(0, -1));
    LiveCellsGraph graph = new LiveCellsGraph(cells);
    testOutput.append(cells);
    testOutput.append(" => ");
    testOutput.append(graph.getCellsWithNumberOfNeighbours(2));
    Approvals.verify(testOutput);
  }

  private StringBuilder findCellsWithSpecifiedNumberOfNeighbours(StringBuilder testOutput, Cell centre, Cell neighbour) {
    List<Cell> cells = new ArrayList<>();
    cells.add(centre);
    cells.add(neighbour);
    testOutput.append(cells);
    LiveCellsGraph graph = new LiveCellsGraph(cells);
    testOutput.append(" => ");
    testOutput.append(graph.getCellsWithNumberOfNeighbours(1));
    testOutput.append("\n");
    return testOutput;
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
