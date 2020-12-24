package gameOfLife;

import gameOfLife.cell.Cell;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class LiveCellsGraphTest {
  @Test
  public void testCanGetCellsWithSpecifiedNumberOfNeighbours() {

  }

  @Test
  public void testCanGetCellsWithOneNeighbour() {
    List<Cell> cells = new ArrayList<>();
    cells.add(new Cell(0, 0));
    cells.add(new Cell(0, 1));

    LiveCellsGraph liveCells = new LiveCellsGraph(cells);
    assertEquals(cells, liveCells.getCellsWithNumberOfNeighbours(1));
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

  // Approvals - how
}
