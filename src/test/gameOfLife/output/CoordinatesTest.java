package gameOfLife.output;

import gameOfLife.TestUtils.CellCoverage;
import gameOfLife.cell.Cell;
import org.approvaltests.Approvals;
import org.junit.Test;

import java.util.List;

public class CoordinatesTest {

  @Test
  public void testWillCreateCoordinateObject() {
    List<Cell> cells = CellCoverage.generateAllPossibleCellsBetweenTwoCells(new Cell(-106, 1), new Cell(95, 22));

    BoardBounds boardBounds = new BoardBounds(cells);

    Approvals.verify(new Coordinates(boardBounds).toString());
  }
}
