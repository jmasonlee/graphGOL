import gameOfLife.cell.Cell;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class LiveCellsGraphTest {
  @Test
  public void testCanBeCreatedWithArrayOfCells() {}

  @Test
  public void testCanGetCellsWithSpecifiedNumberOfNeighbours() {
    List<Cell> cells = new ArrayList<>();
    cells.add(new Cell(0, 0));
    cells.add(new Cell(0, 1));

    LiveCellsGraph liveCells = new LiveCellsGraph(cells);
    assertEquals(cells, liveCells.getCellsWithNumberOfNeighbours(1));
  }

  // Approvals - how
}
