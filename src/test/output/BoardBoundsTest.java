package output;

import cell.Cell;
import org.approvaltests.Approvals;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BoardBoundsTest {
  @Test
  public void testCreateBoard() {
    List<Cell[]> cellsToBeTested = new ArrayList<>();
    cellsToBeTested.add(new Cell[] {});
    cellsToBeTested.add(new Cell[] {new Cell(1, 4)});
    cellsToBeTested.add(new Cell[] {new Cell(-1, -4)});
    cellsToBeTested.add(new Cell[] {new Cell(-2, -5), new Cell(1, 3)});
    cellsToBeTested.add(new Cell[] {new Cell(-4, 6), new Cell(2, 8)});
    cellsToBeTested.add(
        new Cell[] {new Cell(2, 4), new Cell(-1, 8), new Cell(23, 13), new Cell(-4, -1)});

    List<String> testResults = new ArrayList<>();

    for (int i = 0; i < cellsToBeTested.size(); i++) {
      List<Cell> cellList = Arrays.asList(cellsToBeTested.get(i));
      BoardBounds board = new BoardBounds(cellList);
      testResults.add(
          "inputCells: "
              + cellList
              + "\n\t Height:"
              + board.getHeight()
              + "\n\t Width:"
              + board.getWidth()
              + "\n\t Upper Left Coordinates: ("
              + board.getLeftmostXValue()
              + ", "
              + board.getUpperYValue()
              + ")");
    }

    Approvals.verifyAll("", testResults);
  }
}
