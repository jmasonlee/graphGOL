package gameOfLife.output;

import gameOfLife.TestUtils.CellCoverage;
import gameOfLife.cell.Cell;
import junit.framework.TestCase;
import org.approvaltests.Approvals;
import org.approvaltests.namer.NamedEnvironment;
import org.approvaltests.namer.NamerFactory;

public class RowDrawerTest extends TestCase {

  public void testWillDrawLargerCellsAsNumCharsInCoordinatesIncreases() {
    Cell[][] cases = new Cell[][]{
        //base case
        new Cell[]{new Cell(2,4), new Cell(5,9)},
        //two digits on x
        new Cell[]{new Cell(12, 4),new Cell(15, 9)},
        //two digits on y
        new Cell[]{new Cell(2, 14),new Cell(5, 19)},
        //three digits on x
        new Cell[]{new Cell(102, 4),new Cell(105, 9)},
        //three digits on y
        new Cell[]{new Cell(2, 104),new Cell(5, 109)},
        //four digits on x
        new Cell[]{new Cell(1002, 4),new Cell(1005, 9)},
        //four digits on y
        new Cell[]{new Cell(2, 1004),new Cell(5, 1009)},
    };

    for (Cell[] aCase : cases) {
      try (NamedEnvironment environment = NamerFactory.withParameters(aCase)) {
        Coordinates coordinates = CellCoverage.createCoordinates(aCase[0], aCase[1]);
        Approvals.verify(RowDrawer.drawEmptyRow(coordinates));
      }
    }
  }
}