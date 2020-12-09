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
}
