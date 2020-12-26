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

    List<Cell> cells = new ArrayList<>();
    StringBuilder testOutput = new StringBuilder("\n");

    for(int x = 0; x < neighbourRange.length; x++){
      for(int y = 0; y < neighbourRange.length; y++) {
        if(neighbourRange[x] == 0 && neighbourRange[y] == 0){
          continue;
        }

        Cell centre = calculateCentreBasedOnXandY(x,y);
        cells.add(centre);
        cells.add(new Cell(centre.x+neighbourRange[x], centre.y+neighbourRange[y]));
      }
    }

    LiveCellsGraph graph = new LiveCellsGraph(cells);
    testOutput.append("ONE NEIGHBOUR:\n");
    testOutput.append(BoardOutputter.createBoardOutput(cells));
    testOutput.append("\n Detect Cells With 1 neighbour:\n");
    testOutput.append(BoardOutputter.createBoardOutput(graph.getCellsWithNumberOfNeighbours(1)));

    testOutput.append("\nTWO NEIGHBOURS:\n");
    cells = new ArrayList<>();
    cells.add(new Cell(0, 0));
    cells.add(new Cell(0, 1));
    cells.add(new Cell(0, -1));
    graph = new LiveCellsGraph(cells);
    testOutput.append(BoardOutputter.createBoardOutput(cells));
    testOutput.append("\n Detect Cells With 2 neighbours:\n");
    testOutput.append(BoardOutputter.createBoardOutput(graph.getCellsWithNumberOfNeighbours(2)));
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
