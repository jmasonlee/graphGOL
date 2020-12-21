package output;

import cell.Cell;
import org.approvaltests.Approvals;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class BoardTest {
  @Test
  public void testCreateBoard() {
    Board board = new Board(new ArrayList<>());
    Approvals.verify("height:" + board.getHeight() +
        " width:" + board.getWidth() +
        " Upper Left Coordinates: (" + board.getLeftmostXValue() + ", "+ board.getUpperYValue()+")");
  }

  @Test
  public void testCreateBoardWithOneCell(){
    Board board = new Board(Arrays.asList(new Cell[]{new Cell(1,4)}));
    assertEquals(5, board.getHeight());
    assertEquals(5, board.getWidth());
    assertEquals(1, board.getLeftmostXValue());
    assertEquals(4, board.getUpperYValue());
  }
}
