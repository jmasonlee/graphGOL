package cell;

import cell.Cell;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class CellTest {
  @Test
  public void testEqualityOfTwoCellsWithIdenticalCoordinates() {
    Cell cell1 = new Cell(1, 4);
    Cell cell2 = new Cell(1, 4);

    assertEquals(cell1, cell2);
  }

  @Test
  public void testEqualityOfCellComparedWithItself() {
    Cell cell1 = new Cell(1, 4);

    assertEquals(cell1, cell1);
  }

  @Test
  public void testNotEqualToNotACell() {
    Cell cell1 = new Cell(1, 4);

    assertNotEquals(cell1, "Hello!");
  }
}
