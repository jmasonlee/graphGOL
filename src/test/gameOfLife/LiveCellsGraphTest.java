package gameOfLife;

import gameOfLife.cell.Cell;
import gameOfLife.output.BoardOutputter;
import org.approvaltests.Approvals;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class LiveCellsGraphTest {
  @Test
  public void testCanGetCellsWithSpecifiedNumberOfNeighbours() {
    Integer[] neighbourRange = new Integer[] {- 1,0,1};


    StringBuilder testOutput = new StringBuilder();

    testOutput.append("ONE NEIGHBOUR:\n");
    for(int x = 0; x < neighbourRange.length; x++){
      for(int y = 0; y < neighbourRange.length; y++) {
        if(neighbourRange[x] == 0 && neighbourRange[y] == 0){
          continue;
        }
        List<Cell> cells = new ArrayList<>();
        Cell centre = calculateCentreBasedOnXandY(x,y);
        cells.add(centre);
        cells.add(new Cell(centre.x+neighbourRange[x], centre.y+neighbourRange[y]));
        testOutput.append(cells);
        LiveCellsGraph graph = new LiveCellsGraph(cells);
        testOutput.append(" => ");
        testOutput.append(graph.getCellsWithNumberOfNeighbours(1));
        testOutput.append("\n");
      }
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

  private Cell calculateCentreBasedOnXandY(Integer x, Integer y) {
    int initialX = 0;
    int centreSeparator = 6;
    int gridNumber = x*3 + y;

    return new Cell(initialX + (centreSeparator*gridNumber),0);
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
