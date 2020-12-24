import cell.Cell;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class LiveCellsGraphTest {
  // For a list of cells > 2 apart from eachother, the graph should not have any edges
  // For a list of cells that are not neighbours, but <2 apart, the graph should have COPARENT edges
  // or DISCONNECTED edges
  // For a list of cells that are neighbours, the graph should have NEIGHBOUR, COPARENT, or
  // DISCONNECTED edges
  // Should be able to retrieve a list of cells to live for the next turn

  @Test
  public void testCanBeCreatedWithArrayOfCells() {

  }

  @Test
  public void testCanGetCellsWithSpecifiedNumberOfNeighbours() {
    List<Cell> cells = new ArrayList<>();
    cells.add(new Cell(0,0));
    cells.add(new Cell(0, 1));

    LiveCellsGraph liveCells = new LiveCellsGraph(cells);
    assertEquals(cells, liveCells.getCellsWithNumberOfNeighbours(1));
  }

  //Approvals - how
}
