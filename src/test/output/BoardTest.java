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
    Board board = new Board(new ArrayList<>());
    Approvals.verify("height:" + board.getHeight() + " width:" + board.getWidth());
  }
}
