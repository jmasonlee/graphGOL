import org.approvaltests.Approvals;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class GameOfLifeTest {
  @Test
  public void testEmptyBoard() {
    GameOfLife gameOfLife = new GameOfLife(new ArrayList<>());
    String board = gameOfLife.outputBoard();
    Approvals.verify(board);
  }

  @Test
  public void testOneCell() {
    List<Cell> cells = new ArrayList<>();
    cells.add(new Cell(0,1));
    // make board with one cell alive
    GameOfLife gameOfLife = new GameOfLife(cells);
    Approvals.verify(gameOfLife.outputBoard());
    // step
    // board with one cell should be dead
  }
}
