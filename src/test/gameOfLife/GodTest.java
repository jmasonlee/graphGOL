package gameOfLife;

import gameOfLife.cell.Cell;
import gameOfLife.output.BoardOutputter;
import org.approvaltests.Approvals;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class GodTest {
  @Test
  public void testDoesNotReturnIsolatedCells() {
    LiveCellsGraph liveCellsGraph = new LiveCellsGraph(Arrays.asList(new Cell[]{new Cell(0,1)}));
    assertEquals(new ArrayList<>(), God.livingCellsOnNextTurn(liveCellsGraph));
  }

  @Test
  public void returnsCellsWithEnoughNeighbours(){
    List<Cell> cellWithTwoNeighbours = Arrays.asList(
      new Cell[]{
        new Cell(0,1),
        new Cell(0,0),
        new Cell(0,2)
      });

    StringBuilder toVerify = new StringBuilder(BoardOutputter.createBoardOutput(cellWithTwoNeighbours));
    toVerify.append("\n\n");

    gameOfLife.LiveCellsGraph liveCellsGraph = new gameOfLife.LiveCellsGraph(cellWithTwoNeighbours);

    toVerify.append(liveCellsGraph.getCells());

    Approvals.verify(toVerify);
  }

  @Test
  public void testReturnsCellsWith2Neighbours(){
      List<Cell> cellWithTwoNeighbours = Arrays.asList(
          new Cell[]{
              new Cell(0,1),
              new Cell(0,0),
              new Cell(0,2)
          });

      gameOfLife.LiveCellsGraph liveCellsGraph = new gameOfLife.LiveCellsGraph(cellWithTwoNeighbours);
      assertTrue(God.livingCellsOnNextTurn(liveCellsGraph).contains(cellWithTwoNeighbours.get(0)));
  }
}