package output;

import cell.Cell;
import org.approvaltests.Approvals;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class BoardTest {
  @Test
  public void testCreateBoard() {
    List<Cell> cellsToBeTested = Arrays.asList(new Cell [] {
        new Cell(1,4)
    });

    List<Cell> cellsUnderTest = new ArrayList<>();
    List<String> testResults = new ArrayList<>();

    for(int i = 0; i <= cellsToBeTested.size(); i++){
      Board board = new Board(cellsUnderTest);
      testResults.add("inputCells: "+ cellsUnderTest +
          "\n\t Height:" + board.getHeight() +
          "\n\t Width:" + board.getWidth() +
          "\n\t Upper Left Coordinates: (" + board.getLeftmostXValue() + ", "+ board.getUpperYValue()+")");

      if(i < cellsToBeTested.size()){
        cellsUnderTest.add(cellsToBeTested.get(i));
      }
    }

    Approvals.verifyAll("", testResults);
  }
}
