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
    List<Cell[]> cellsToBeTested = new ArrayList<>();
    cellsToBeTested.add(new Cell[]{});
    cellsToBeTested.add(new Cell[]{new Cell(1,4)});
    cellsToBeTested.add(new Cell[]{new Cell( -1, -4)});

    List<String> testResults = new ArrayList<>();

    for(int i = 0; i < cellsToBeTested.size(); i++){
      List<Cell> cellList = Arrays.asList(cellsToBeTested.get(i));
      Board board = new Board(cellList);
      testResults.add("inputCells: "+ cellList +
          "\n\t Height:" + board.getHeight() +
          "\n\t Width:" + board.getWidth() +
          "\n\t Upper Left Coordinates: (" + board.getLeftmostXValue() + ", "+ board.getUpperYValue()+")");
    }

    Approvals.verifyAll("", testResults);
  }

  @Test
  public void testCreateBoardWithCellsMoreThan5ApartForHeight(){
    Board board = new Board(Arrays.asList(new Cell[]{new Cell(-2,-5), new Cell(1, 3)}));
    assertEquals(8, board.getHeight());
    assertEquals(5, board.getWidth());
    assertEquals(-2, board.getLeftmostXValue());
    assertEquals(3, board.getUpperYValue());
  }
}
