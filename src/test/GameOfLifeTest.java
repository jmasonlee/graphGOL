import gameOfLife.cell.Cell;
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
  public void testOneCellDies() {
    List<Cell> cells = new ArrayList<>();
    cells.add(new Cell(0,1));
    // make board with one gameOfLife.cell alive
    GameOfLife gameOfLife = new GameOfLife(cells);
    String initialBoard = gameOfLife.outputBoard();
    // step
    gameOfLife.next();
    // board with one gameOfLife.cell should be dead
    String finalBoard = gameOfLife.outputBoard();

    StringBuilder sb = new StringBuilder();
    sb.append("Start: \n");
    sb.append(initialBoard + "\n");
    sb.append("End: \n");
    sb.append(finalBoard);

    Approvals.verify(sb.toString());
  }
}
