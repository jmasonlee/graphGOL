import cell.Cell;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class FooTest {
  @Test
  public void testDoesNotReturnIsolatedCells() {
    LiveCellsGraph liveCellsGraph = new LiveCellsGraph(Arrays.asList(new Cell[]{new Cell(0,1)}));
    assertEquals(new ArrayList<>(), Foo.livingCellsOnNextTurn(liveCellsGraph));
  }

  @Test
  public void testReturnsCellsWith2Neighbours(){
      List<Cell> cellWithTwoNeighbours = Arrays.asList(
          new Cell[]{
              new Cell(0,1),
              new Cell(0,0),
              new Cell(0,2)
          });

      LiveCellsGraph liveCellsGraph = new LiveCellsGraph(cellWithTwoNeighbours);
      assertTrue(Foo.livingCellsOnNextTurn(liveCellsGraph).contains(cellWithTwoNeighbours.get(0)));
  }
}