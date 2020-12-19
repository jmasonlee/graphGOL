package output;

import cell.Cell;
import org.approvaltests.Approvals;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class BoardTest {
  @Test
  public void testCreateBoard(){
      List<Cell> cellsForBoard = Arrays.asList(new Cell[]{ new Cell(-4,-3), new Cell(-1, 0), new Cell(2,6)});
      Board board = new Board(cellsForBoard);
      Approvals.verify("height:"+board.getHeight()+" width:"+board.getWidth());
  }
}